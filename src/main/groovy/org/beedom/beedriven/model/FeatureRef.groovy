package org.beedom.beedriven.model

import java.util.List;
import java.util.Map;


/**
 * 
 * @author kovax
 *
 */
class FeatureRef {

    /**
     * The name of the Feature
     * Must be identcal with the Feature file name (without exetension) 
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

}
