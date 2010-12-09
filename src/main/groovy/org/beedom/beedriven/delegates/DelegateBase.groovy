package org.beedom.beedriven.delegates


class DelegateBase {
    def nesting = 0

    static sharedInstance
    def description
    
    def init(String desc) {
        featureDesc = desc
        nesting++
        println "FeatureDelegate.init(): $featureDesc $nesting."
    }
    
    def destroy() {
        nesting--
    }

    
    def doThisAlways(dslAlias, runClosure, method, desc, cl ) {
        if(dslAlias) println "$dslAlias($method) $featureDesc - $desc"
        else println "$method $featureDesc - $desc"

        if(cl && runClosure) {
            cl()
        }
    }
    
    def action(String desc) {
        
    }
}
