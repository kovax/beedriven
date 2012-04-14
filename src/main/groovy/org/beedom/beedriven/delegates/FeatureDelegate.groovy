package org.beedom.beedriven.delegates


import groovy.util.logging.Slf4j;

import org.beedom.dslforge.BindingConvention
import org.beedom.dslforge.SimpleReportingDelegate;


@Slf4j
class FeatureDelegate extends SimpleReportingDelegate {
	
    def static dslKey = "feature"
    def static delegateMethods = ["in_order", "as_a", "i_want", "requirement"]

    public FeatureDelegate(String name) {
        initDelegate(name)
    }
    
}
