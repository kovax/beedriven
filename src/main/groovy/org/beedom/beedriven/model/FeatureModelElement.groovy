package org.beedom.beedriven.model

import groovy.xml.MarkupBuilder;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class FeatureModelElement {

    protected static final templateName = "FeatureModelElement" 
    
    /**
     * The name of the Scenario or Feature.
     * Must be identcal with the Scenario/Feature file name (without extension) 
     * or with name of the Scenario inside of the Feature file.
     * 
     * TODO: apply rule to accept characters which are valid for file name only (this is only for gui probably)
     */
    String name = ""

    /**
    * cannot be null
    */
    File dslFile

    /**
     * Regexp to split CamelCase string into words
     *     
     * @param s the camelCase string, normali a file name
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
}
