package org.beedom.beedriven.delegates


import groovy.util.logging.Slf4j;

import org.beedom.dslforge.BindingConvention


@Slf4j
class FeatureDelegate extends DelegateBase {
	
    def static dslKey = "feature"

	public FeatureDelegate() {
        init(null)
    }

    public FeatureDelegate(String name) {
        init(name)
    }

    def in_order(String desc) {
        in_order(desc, null)
    }

    def in_order(String desc, Closure cl) {
        reporter.write(0, "Feature:", name)
        reporter.write(1, dslAlias ?: "In order", desc)
        if(cl && !context.dryRun) { cl() }
    }

    def as_a(String desc) {
        as_a(desc,null)
    }

    def as_a(String desc, Closure cl) {
        reporter.write(1, dslAlias ?: "As a", desc)
        if(cl && !context.dryRun) { cl() }
    }

    def i_want(String desc) {
        i_want(desc,null)
    }

    def i_want(String desc, Closure cl) {
        reporter.write(1, dslAlias ?: "I want", desc)
        if(cl && !context.dryRun) { cl() }
    }

    def requirement(String desc) {
        requirement(desc,null)
    }

    def requirement(String desc, Closure cl) {
        reporter.write(1, dslAlias ?: "Requirement: ", desc)
        if(cl && !context.dryRun) { cl() }
    }
}
