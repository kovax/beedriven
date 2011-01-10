package org.beedom.beedriven.model

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author kovax
 *
 */
class FeatureRef {
    
    protected static Logger log = LoggerFactory.getLogger(FeatureRef.class);

    /**
     * The name of the Feature
     * Must be identical with the Feature file name (without extension) 
     * 
     * TODO: aply rule to accept characters which are valid for file namea
     */
    String name = ""

    /**
     * List of Scenarios
     */
    Map<String, ScenarioRef> scenarios = [:]
    
    /**
     * Any of these feature is selected (nice-to-have)
     */
    Map<String, FeatureRef> optional = [:]

    /**
     * All these features are selected
     */
    Map<String, FeatureRef> mandatory = [:]

    /**
     * Only one of these features is selected
     */
    Map<String, FeatureRef> alternative = [:]
    
    /**
     * At least one of these features is selected
     */
    Map<String, FeatureRef> or = [:]

    /**
     *     
     * @param name
     * @return
     */
    public File createFeatureFile(String name) {
    }

    private static List getFeatureRef(FeatureRef currentFeature, String name) {
        log.info "Searching $name in ${currentFeature.name}"

        if(currentFeature.mandatory."$name") {
            return ["mandatory", currentFeature.mandatory."$name"]
        }
        else if(currentFeature.optional."$name") {
            return ["optional", currentFeature.optional."$name"]
        }
        else if(currentFeature.alternative."$name") {
            return ["alternative", currentFeature.alternative."$name"]
        }
        else if(currentFeature.or."$name") {
            return ["or", currentFeature.or."$name"]
        }
        else {
            return null
        }
    }
}
