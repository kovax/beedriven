package org.beedom.beedriven.model

import java.util.List;
import java.util.Map;
import java.io.File;

import javax.naming.InvalidNameException;



/**
 * 
 * @author kovax
 *
 */
class FeatureModel extends FeatureRef {

    static File root = new File("src/test/scripts")

    /**
     * 
     */
    String  version = ""

    def sortByTypeThenName = { a, b ->
        a.isFile() != b.isFile() ? a.isFile() <=> b.isFile() : a.name <=> b.name
    }

    static String splitCamelCase(String s) {
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
    

    def scanFiles( ) {

        root.traverse( sort: sortByTypeThenName ) { File f ->
            //println f.toURI().toURL()
            if( f.isFile() ) {
                def fName = f.getName()
                def extIndex = fName.indexOf(".")
                def ext = fName.substring(extIndex+1)
                def name = fName.substring(0,extIndex)

                switch(ext) {
                    case "feature" :
                        println "feature: " + splitCamelCase(name)
                        break

                    case "scenario" :
                        println "scenario: " + splitCamelCase(name)
                        break

                    case "fm" :
                        println "feature model: " + splitCamelCase(name)
                        break

                    default :
                        throw new InvalidNameException("Extension '$ext' is invalid, must be either feature, scenario or fm")
                        break
                }
            }
            else  {
                def name = f.getName()
                def ref = getFeatureRef(this, name)
                
                if(!ref) {
                    println "directory is new : "+splitCamelCase(name)
                    ref = new FeatureRef(name: name)
                }
                else {
                    println "directory exists : "+splitCamelCase(name)
                }
            }
        }
    }
}
