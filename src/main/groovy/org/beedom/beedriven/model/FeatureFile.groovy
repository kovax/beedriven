package org.beedom.beedriven.model

import static org.beedom.beedriven.model.FeatureModelElement.Type.*
import groovy.text.GStringTemplateEngine
import groovy.util.ConfigObject;
import groovy.util.logging.Slf4j

import java.io.File
import java.util.Map

import javax.naming.InvalidNameException

import org.beedom.beedriven.model.FeatureModelElement.Type
import org.beedom.dslforge.DSLEngine
import org.beedom.dslforge.ReportRenderer;
import org.beedom.dslforge.SimpleRenderer;
import org.beedom.dslforge.SimpleRenderer.ReportType;


/**
 * 
 * @author kovax
 *
 */
@Slf4j
class FeatureFile extends FeatureModelElement {

    protected static final template = FeatureFile.class.getResource("FeatureRef.template")    
    protected static final templateName = "FeatureRef"

    /**
     * This is only for MetaBuilder support
     */
    FeatureFile() {
        //TODO: make this configurable
        config = new ConfigSlurper("development").parse(new File("conf/DSLConfig.groovy").toURI().toURL())
    }

    /**
     * 
     * @param dir is the directory
     */
    FeatureFile(File dir) {
        assert dir && dir.exists() && dir.isDirectory(), "Directory '${dir.getName()}' must exists"

        this.name = getNameFromFile(dir)
        dslFile = new File(dir, name+".feature")

        assert dslFile.exists(), "Directory '${dir.getName()}' must contain a file: ${name}.feature"
        
        //TODO: make this configurable
        config = new ConfigSlurper("development").parse(new File("conf/DSLConfig.groovy").toURI().toURL())
    }

    /**
     * List of Scenarios which are relevant for this Feature
     */
    Map<String, ScenarioFile> scenarios = [:]


    /**
     * Any of these SubFeatures is selected (nice-to-have)
     */
    Map<String, FeatureFile> optional = [:]

    /**
     * All these SubFeatures are selected
     */
    Map<String, FeatureFile> mandatory = [:]

    /**
     * Only one of these SubFeatures is selected
     */
    Map<String, FeatureFile> alternative = [:]

    /**
     * At least one of these SubFeatures is selected
     */
    Map<String, FeatureFile> or = [:]

    /*
    def streamit() {
        def gstring = new GStringTemplateEngine()
        def gsource = '''Dear <%= name %>,
        Text is created for <% if (gstring) out << 'GStringTemplateEngine' else out << 'other template engine'%>.'''
        def gbinding = [name: 'mrhaki', gstring: true]
        def goutput = gstring.createTemplate(gsource).make(gbinding).toString()
    }
    */
    
    public boolean checkFile(File f) {
        return f.exists()
    }


    /**
     * 
     */
    private Closure sortByTypeThenName = { a, b ->
        a.isFile() != b.isFile() ? a.isFile() <=> b.isFile() : a.name <=> b.name
    }
    

    /**
     * 
     * @param feature
     * @param name
     * @return
     */
    private static Map getSubFeature(FeatureFile feature, String name) {
        if(feature.mandatory."$name") {
            return [type: "mandatory", ref: feature.mandatory."$name"]
        }
        else if(feature.optional."$name") {
            return [type: "optional", ref: feature.optional."$name"]
        }
        else if(feature.alternative."$name") {
            return [type: "alternative", ref: feature.alternative."$name"]
        }
        else if(feature.or."$name") {
            return [type: "or", ref: feature.or."$name"]
        }
        else {
            return null
        }
    }


    /**
     * 
     * @param dir
     */
    public void scanFiles( File dir ) {
        dir.traverse( sort: sortByTypeThenName, maxDepth: 0 ) { File f ->
            log.info "scanFiles: " + f.toURI().toURL().toString()
            def fullName = f.name

            if( f.isFile() ) {
                def extIndex = fullName.indexOf(".")
                def fileExt = fullName.substring(extIndex+1)
                def fileName = fullName.substring(0,extIndex)

                switch(fileExt) {
                    case "feature" :
                        log.info "feature file : " + splitCamelCase(fileName)
                        assert name == fileName, "$fullName should not exists, only one feature file per direcory is allowed"
                        if(dslFile) {
                            assert dslFile.equals(f)
                        }
                        else {
                            assert f.exists()
                            dslFile = f
                        }
                        break

                    case "scenario" :
                        ScenarioFile sce = scenarios[fileName]
                        if(sce) {
                            log.info "scenario file exists : " + splitCamelCase(fileName)
                            sce.dslFile = f
                        }
                        else {
                            log.info "add scenario '${splitCamelCase(fileName)}'"
                            scenarios.put(fileName, new ScenarioFile(f))
                        }
                        break

                    case "fm" :
                        log.info "feature model file: " + splitCamelCase(fileName)
                        break

                    default :
                        throw new InvalidNameException("Extension '$fileExt' is invalid, must be either feature, scenario or fm")
                        break
                }
            }
            else if( f.isDirectory() ) {
                FeatureFile subFeature = getSubFeature(this,fullName)?.ref //returned map can be null

                if(subFeature) {
                    log.info "subfeature exists : "+splitCamelCase(fullName)
                }
                else {
                    log.info "add mandatory subfeature : "+splitCamelCase(fullName)
                    subFeature = new FeatureFile(f)
                    mandatory.put(fullName,subFeature)
                }

                subFeature.scanFiles(f)
            }
            else {
                throw new RuntimeException("File is unknown type:" +f)
            }
        }
    }


    /**
     * 
     */
    public void dumpToMetaBuilder() {
        dumpToMetaBuilder( new GStringTemplateEngine() )
    }

    
    /**
     * 
     * @param templEngine
     */
    public void dumpToMetaBuilder(GStringTemplateEngine templEngine) {
        assert templEngine
        
        def binding = [SchemaName: templateName, name: this.name, 
            scenarioList: "my scenarios",
            mandatoryList: null,
            optionalList: null,
            alternativeList: null,
            orList: null
        ]

        def mbFormat = templEngine.createTemplate(template).make(binding)

        println mbFormat.toString()
    }


    /**
     * 
     * @param options
     */
    private static void setDefaultTraverseOptions(Map options) {
        assert options != null, "options must not be null"

        if(options.get("type")     == null) { options.type     = ALL   }
        if(options.get("deep")     == null) { options.deep     = true  }
        if(options.get("dry")      == null) { options.dry      = false }
        if(options.get("isolated") == null) { options.isolated = true  }
    }


    /**
     * 
     * @param map
     * @param mapName
     * @param cl
     * @return
     */
    private def traverseMap(Map options, String mapName, Closure cl) {
        final Type selection = options.type
        final Boolean deep   = options.deep

        this."$mapName".each { String name, modelElement ->
            if(modelElement instanceof FeatureFile ) {
                //Execute closure for the chosen type only
                if(selection == ALL || selection == FEATURE) {
                    cl(mapName, modelElement)
                }

                //Go deeper in the tree
                if(deep) {
                    modelElement.traverse(options, cl)
                }
            }
            else if(modelElement instanceof ScenarioFile) {
                //Execute closure for the chosen type only
                if(selection == ALL || selection == SCENARIO) {
                    cl(mapName, modelElement)
                }
            }
            else {
                throw new RuntimeException("Unhandled class: " + modelElement.class.name)
            }
        }
    }


    public void traverse(Closure cl) {
        traverse([:], cl)
    }


    public void traverse(Map options, Closure cl) {
        setDefaultTraverseOptions(options)

        log.info "traverse $name - options: " + options

        traverseMap(options, "mandatory", cl)
        traverseMap(options, "optional", cl)
        traverseMap(options, "alternative", cl)
        traverseMap(options, "or", cl)

        traverseMap(options, "scenarios", cl)
    }


    /**
     * If isolated run feature script before each scenario script
     * else run feature script only once before all scenario scripts.
     * Also initialise DSL context/binding for each feature script run.
     * 
     * @param feature the current feature to be run
     * @param isolated
     * @param dry do not execute closures
     * @return
     */
    private void executeScenarios(boolean isolated, boolean dry, ReportType reportType) {
        //TODO: share engine and context for deep execution; make it configurable
        DSLEngine engine  = null
        Binding   context = null

        def file     = createNewReportFile(config.beedriven.reportDir, dslFile.parentFile.path, reportType)
        def writer   = new PrintWriter(new BufferedWriter(new FileWriter(file)))
        def reporter = new SimpleRenderer(writer, reportType)

        scenarios.each { k,scenario ->
            if(isolated || (!isolated && !engine)) {
                context = new Binding( dryRun: dry )
                engine  = new DSLEngine(context: context, dslConfig: config, reporter: reporter)

                engine.run(dslFile.path)
            }

            log.debug "Execute scenario: $scenario.name"
            //scenario.createNewReportFile(config.beedriven.reportDir, dslFile.parentFile.path, reportType)
            engine.run(scenario.dslFile.path)

            //scenario was executed if it was not a dry run
            scenario.executed = !dry
        }

        writer.close()
    }


    /**
     *     
     */
    public void execute() {
        execute([:])
    }


    /**
     * 
     * @param options
     */
    public void execute(Map options) {
        log.info "execute feature: ${dslFile.path}"
        
        options.report = options.report ?: SimpleRenderer.ReportType.TXT

        executeScenarios(options.isolated, options.dry, options.report)

        if(options.deep) {
            setDefaultTraverseOptions(options)
            options.type = FEATURE

            log.debug "execute deep options:$options"

            traverse( options ) { mapName, feature ->
                feature.executeScenarios(options.isolated, options.dry, options.report)
            }
        }
    }
}
