package org.beedom.beedriven.gradle

import java.util.logging.Logger;

import groovy.lang.Closure;

class BeeDrivenConvention {
    protected static Logger log = Logger.getLogger(BeeDrivenConvention.class.getName());
    
    Closure dsl

    def bdd( Closure cl ) {
        beedriven(cl)
    }

    def beedriven( Closure cl ) {
        dsl = cl
        log.info( "Dump dsl closure:" + dsl.dump() )
    }
}
