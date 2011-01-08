package org.beedom.beedriven.delegates


import org.beedom.dslforge.BindingConvention


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
        doThisAlways("in_order", desc, cl)
    }
    
    def as_a(String desc) {
        as_a(desc,null)
    }

    def as_a(String desc, Closure cl) {
        doThisAlways("as_a", desc, cl)
    }
    
    def i_want(String desc) {
        i_want(desc,null)
    }

    def i_want(String desc, Closure cl) {
        doThisAlways("i_want", desc, cl)
    }
    
    def requirement(String desc) {
        requirement(desc,null)
    }

    def requirement(String desc, Closure cl) {
        doThisAlways("requirement", desc, cl)
    }
}
