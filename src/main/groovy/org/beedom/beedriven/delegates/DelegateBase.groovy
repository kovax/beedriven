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
        println "$name: initialise - newsting level = $nesting."
    }
    
    def destroy() {
        nesting--
    }

    def doThisAlways(String method, String desc, Closure cl ) {
        doThisAlways(null, method, desc, cl)
    }

    def doThisAlways(String dslAlias, String method, String desc, Closure cl ) {
        if(dslAlias) println "$dslAlias($method) $name - $desc"
        else println "$name: $method - $desc"

        if(cl && !context.dryRun) {
            cl()
        }
    }

}
