package org.beedom.beedriven.delegates

import groovy.util.logging.Slf4j;

/**
 * 
 * @author kovax
 *
 */
@Slf4j
class ScenarioDelegate extends DelegateBase {

    def static dslKey = "scenario"

    public ScenarioDelegate() {
        init(null)
    }

    public ScenarioDelegate(String name) {
        init(name)
    }

    /**
     * Alias list for methods. The current values are Hungarian translation
     * and not-so-good English alternatives. The 'main' keyword denotes the
     * aliases used for dslKey
     */
    def static aliases = [main:  ["szenárió", "process"],    
                          given: ["adott",    "provided"],
                          and:   ["és",       "plus"],
                          when:  ["amikor",   "although"],
                          then:  ["akkor",    "consequently"]]

    def description(String desc) {
        reporter.write(2, dslAlias ?: "Description", desc)
    }

    def action(String desc) {
        reporter.write(2, dslAlias ?: "Action", desc)
    }

    def given(String desc) {
    	given(desc,null)
    }

    def given(String desc, Closure cl) {
        //TODO: Scenario report is not optimal
        reporter.write(1, "Scenario:", name)

        reporter.write(2, dslAlias ?: "Given", desc)
        if(cl && !context.dryRun) { cl() }
    }
    
    def and(String desc) {
    	and(desc,null)
    }

    def and(String desc, Closure cl ) {
        reporter.write(2, dslAlias ?: "And", desc)
        if(cl && !context.dryRun) { cl() }
    }

    def when(String desc) {
    	when(desc,null)
    }

    def when(String desc, Closure cl ) {
        reporter.write(2, dslAlias ?: "When", desc)
        if(cl && !context.dryRun) { cl() }
    }

    def then(String desc) {
    	then(desc,null)
    }

    def then(String desc, Closure cl ) {
        reporter.write(2, dslAlias ?: "Then", desc)
        if(cl && !context.dryRun) { cl() }
    }
}
