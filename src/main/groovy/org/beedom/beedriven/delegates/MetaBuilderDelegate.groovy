package org.beedom.beedriven.delegates


import java.util.logging.Logger;

import org.beedom.dslforge.BindingConvention;

import groovytools.builder.MetaBuilder


/**
 * 
 * @author zs.myth
 */
class MetaBuilderDelegate {

    protected static Logger log = Logger.getLogger(MetaBuilderDelegate.class.getName());
    
	def initialised = false
	
	def static dslKey = "define"

	def processClosure(Closure cl) {

	    if(!initialised) {
	    	context.metaBuilder = new MetaBuilder()

            context.mbSchemaFiles.each { fPath ->
                new GroovyShell( context ).evaluate( new File(fPath) )
            }
	    	initialised = true
		}

	    def objs = context.metaBuilder.buildList(cl)
    	BindingConvention.bindObjectList( context, objs, context.mbObjectKeys )
	}
}
