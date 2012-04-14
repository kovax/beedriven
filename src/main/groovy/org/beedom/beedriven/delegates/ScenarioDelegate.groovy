package org.beedom.beedriven.delegates

import org.beedom.dslforge.SimpleReportingDelegate;

import groovy.util.logging.Slf4j;

/**
 * 
 * @author kovax
 *
 */
@Slf4j
class ScenarioDelegate extends SimpleReportingDelegate {

    def static dslKey = "scenario"
    def static delegateMethods = ["given", "when", "then", "and", "but", "description", "action"]


    public ScenarioDelegate(String name) {
        initDelegate(name)
    }
    
    /**
     * Alias list for methods. The current values are Hungarian translation
     * and not-so-good English alternatives. The 'main' keyword denotes the
     * aliases used for dslKey
     */
    def static aliases = [main:  ["szenárió", "process"],    
                          given: ["adott",    "provided"],
                          and:   ["és",       "plus"],
                          but:   ["de"],
                          when:  ["amikor",   "although"],
                          then:  ["akkor",    "consequently"]]
    
    def given(Map map) {
        println "---------------- $map"
        
    }

}
