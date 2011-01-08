package org.beedom.beedriven.delegates


class DelegateBase {
    String name = ""
    String description = ""

    def nesting = 0
    static sharedInstance
    
    def init(String n) {
        if(n) {
            name = n
        }
        nesting++
        println "init: $name $nesting."
    }
    
    def destroy() {
        nesting--
    }

    def doThisAlways(String method, String desc, Closure cl ) {
        doThisAlways(null, null, method, desc, cl)
    }

    def doThisAlways(String dslAlias, Boolean runClosure, String method, String desc, Closure cl ) {
        if(dslAlias) println "$dslAlias($method) $name - $desc"
        else println "$name: $method - $desc"

        if(cl && runClosure) {
            cl()
        }
    }

}
