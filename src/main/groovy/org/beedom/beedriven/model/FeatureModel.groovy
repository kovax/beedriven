package org.beedom.beedriven.model

import groovy.xml.MarkupBuilder;

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

    protected static final templateName = "ProductRef"

    /**
     * 
     */
    String  version = ""
    
    
    def dumpFM(String dir) {

        def destFile = new FileOutputStream(dir+'/${name}_dump.fm')
        def destWriter = new OutputStreamWriter(destFile,"UTF-8")
        def xml = new MarkupBuilder(destWriter)

        destWriter << '<?xml version="1.0" encoding="UTF-8"?>'
    }

}
