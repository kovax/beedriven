package org.beedom.beedriven

import java.util.List;

/**
 * FeatureContainer is the base class for FeatureModels
 * 
 * @author kovax
 *
 */
class FeatureContainer {

   String name = ""

    /**
     * None of these feature is must be selected (nice-to-have?)
     */
    List<Feature> optional = []

    /**
     * All this features are must be selected
     */
    List<Feature> mandatory = []

    /**
     * Only one of these features must be selected
     */
    List<Feature> alternative = []
    
    /**
     * At least one of these features must be selected
     */
    List<Feature> or = []
}
