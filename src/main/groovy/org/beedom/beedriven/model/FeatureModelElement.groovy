package org.beedom.beedriven.model

import groovy.util.ConfigObject;
import groovy.util.logging.Slf4j

import java.io.File

import org.beedom.dslforge.ReportRenderer;
import org.beedom.dslforge.SimpleRenderer;

/**
 * 
 * @author kovax
 *
 */
@Slf4j
abstract class FeatureModelElement {

    protected static final String templateName = "FeatureModelElement"
    
    public enum Type { ALL, REQUIRED, FEATURE, SCENARIO }

    /**
     * The name of the Scenario or Feature.
     * Must be identical with the Scenario/Feature file name (without extension)
     * or with name of the Scenario inside of the Feature file.
     * 
     * TODO: apply rule to accept characters which are valid for file name only (this is only for gui probably)
     */
    String name = ""

    /**
    *
    */
    File dslFile = null

    /**
     * In 'alternative' and 'or' collection the Feature must be selected in order to compute the proper semantics
     */
    Boolean selected
    
    
    protected ConfigObject config
    
    
    /**
     * Regexp to split CamelCase string into words
     *     
     * @param s the camelCase string, normally a file name
     * @return string split into words
     */
    public static String splitCamelCase(String s) {
        return s.replaceAll(
            String.format(
                "%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"
            ),
            " "
        );
    }
    
    /**
     * 
     * @param f
     * @return
     */
    public String getNameFromFile(File f) {
        if( f.isFile() ) {
            def fullName = f.getName()
            def extIndex = fullName.indexOf(".")
            def fileExt = fullName.substring(extIndex+1)
            
            return fullName.substring(0,extIndex)
        }
        else {
            f.getName()
        }
    }
    

    public File createNewReportFile(String reportDir, String fileDirPath, SimpleRenderer.ReportType type) {
        //make the full directory structure
        new File(reportDir+"/"+fileDirPath).mkdirs()
        
        log.debug "create file:" + reportDir+"/"+fileDirPath+"/"+name+"."+SimpleRenderer.getFileExt(type)
        def f = new File(reportDir+"/"+fileDirPath+"/"+name+"."+type)
        f.createNewFile()
        return f
    }
}
