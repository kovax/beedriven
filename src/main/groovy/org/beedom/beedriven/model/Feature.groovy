package org.beedom.beedriven.model


import java.util.List;

/**
 * 
 * @author kovax
 *
 */
class Feature {

    String name = ""
    String description = ""
    String version = ""
    
    /**
     * Any of these feature is selected (nice-to-have)
     */
    List<Feature> optional = []

    /**
     * All these features are selected
     */
    List<Feature> mandatory = []

    /**
     * Only one of these features is selected
     */
    List<Feature> alternative = []
    
    /**
     * At least one of these features is selected
     */
    List<Feature> or = []
}
