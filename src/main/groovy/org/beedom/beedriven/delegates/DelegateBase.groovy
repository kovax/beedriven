package org.beedom.beedriven.delegates


import groovy.util.logging.Slf4j

/**
 * 
 * @author kovax
 *
 */
@Slf4j
class DelegateBase {

    String name = ""
    String description = ""

    private int nesting = 0
    static def sharedInstance

    public void init(String n) {
        if(n) { name = n }
        nesting++
        log.info "initialise - $name, nesting level = $nesting."
    }
    
    def destroy() {
        nesting--
    }
}
