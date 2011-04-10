package org.beedom.beedriven.model

import groovy.text.GStringTemplateEngine;

import groovy.xml.MarkupBuilder;

import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.naming.InvalidNameException;

import java.io.File;

import org.beedom.beedriven.model.FeatureModelElement.Type
import static org.beedom.beedriven.model.FeatureModelElement.Type.*

import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.beedom.dslforge.DSLEngine;


/**
 * 
 * @author kovax
 *
 */
class FeatureRef extends FeatureModelElement {

    protected static Logger log = LoggerFactory.getLogger(FeatureRef.class);
    
    protected static final template = FeatureRef.class.getResource("FeatureRef.template")
    
    protected static final templateName = "FeatureRef"


    /**
     * This is only for MetaBuilder support
     */
    FeatureRef() {
    }

    /**
     * 
     * @param f is the directory
     */
    FeatureRef(File dir) {
        assert dir && dir.exists() && dir.isDirectory(), "Directory '${dir.getName()}' must exists"

        this.name = getNameFromFile(dir)
        dslFile = new File(dir, name+".feature")

        assert dslFile.exists(), "Directory '${dir.getName()}' must contain a file: ${name}.feature"
    }

    /**
     * List of Scenarios which are relevant for this Feature
     */
    Map<String, ScenarioRef> scenarios = [:]


    /**
     * Any of these SubFeatures is selected (nice-to-have)
     */
    Map<String, FeatureRef> optional = [:]

    /**
     * All these SubFeatures are selected
     */
    Map<String, FeatureRef> mandatory = [:]

    /**
     * Only one of these SubFeatures is selected
     */
    Map<String, FeatureRef> alternative = [:]

    /**
     * At least one of these SubFeatures is selected
     */
    Map<String, FeatureRef> or = [:]

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
     * @param name
     * @return
     */
    public File createFeatureFile(String name) {
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
    private static Map getSubFeature(FeatureRef feature, String name) {
        log.info "Searching subfeature $name in ${feature.name}"
        
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
            log.debug f.toURI().toURL().toString()
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
                        ScenarioRef sce = scenarios[fileName]
                        if(sce) {
                            log.info "scenario file exists : " + splitCamelCase(fileName)
                            sce.dslFile = f
                        }
                        else {
                            log.info "add scenario '${splitCamelCase(fileName)}'"
                            scenarios.put(fileName, new ScenarioRef(f))
                        }
                        break

                    case "fm" :
                        log.info "feature model file: " + splitCamelCase(fileName)
                        break

                    default :
                        throw new InvalidNameException("Extension '$ext' is invalid, must be either feature, scenario or fm")
                        break
                }
            }
            else if( f.isDirectory() ) {
                FeatureRef subFeature = getSubFeature(this,fullName)?.ref //returned map can be null

                if(subFeature) {
                    log.info "subfeature exists : "+splitCamelCase(fullName)
                }
                else {
                    log.info "add mandatory subfeature : "+splitCamelCase(fullName)
                    subFeature = new FeatureRef(f)
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

    
    private static def setDefaultTraverseOptions(options) {
        assert options!=null, "options must not be null"

        options.selection = options.selection ?: ALL //Elvis operator
        options.deep = options.deep ?: true //Elvis operator
    }


    /**
     * 
     * @param map
     * @param mapName
     * @param cl
     * @return
     */
    private def traverseMap( Map options, String mapName, Closure cl ) {
        final Type selection = options.type
        final Boolean deep   = options.deep

        this."$mapName".each { String name, modelElement ->
            if(modelElement instanceof FeatureRef ) {

                //Execute closure for the chosen type only
                if(selection == ALL || selection == FEATURE) {
                    cl(mapName, modelElement)
                }

                //Go deeper in the tree
                if(deep) {
                    modelElement.traverse(options, cl)
                }
            }
            else if(modelElement instanceof ScenarioRef) {
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

        log.info "$name - options: " + options

        final Type selection = options.selection

        assert selection, "traverse requires options.selection"
        
        traverseMap(options, "mandatory", cl)
        traverseMap(options, "optional", cl)
        traverseMap(options, "alternative", cl)
        traverseMap(options, "or", cl)

        traverseMap(options, "scenarios", cl)
    }

    /**
     * If isolated run feature script before each scenario script
     * else run feature script only once before all scenario scripts
     * Also initialise DSL context/binding for each feature script run
     * 
     * @param feature the current feature to be run
     * @param isolated
     * @param dry do not execute closures
     * @return
     */
    private static def executeScenarios(feature, isolated, dry) {
        DSLEngine engine = null

        feature.scenarios.each { k,v ->
            if(isolated || (!isolated && !engine)) {
                engine = new DSLEngine(new Binding(dryRun:dry))
                engine.run(feature.dslFile.absolutePath)
            }

            engine.run(v.dslFile.absolutePath)
        }
    }


    public void execute() {
        execute([:])
    }

    
    public void execute(Map options) {
        final Boolean deep     = options.deep      ?: false
        final Boolean dry      = options.dry       ?: false
        final Boolean isolated = options.isolated  ?: true

        log.info "Executing from feature: $dslFile.absolutePath"

        executeScenarios(this, isolated, dry)

        if(deep) {
            traverse( selection: FEATURE, deep: true ) { mapName, modelElement ->
                executeScenarios(modelElement, isolated, dry)
            }
        }
    }
}
