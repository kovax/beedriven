package org.beedom.beedriven.test


import org.beedom.dslforge.DSLEngine

import groovy.lang.Binding

import org.junit.Before

class TestBase {

	def dsle
	def context

	@Before
	public void init() {
		context = new Binding()
        context.mbSchemaFiles = ["src/main/conf/FeatureModelSchema.groovy"]
        
        if(!dsle) {
            dsle = new DSLEngine(context)
        }
	}
}
