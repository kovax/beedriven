package org.beedom.beedriven.test


import org.junit.Test

class FeatureModelTests extends TestBase {

	@Test
	public void runModel() {
		dsle.run("WebShop.fm")

        assert context.myFirstWebShop
        assert context.myFirstWebShop.optional.CustomerSelfManagement
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Login
	}
    
    @Test
    public void scanRoot() {
        dsle.run("WebShop.fm")

        context.myFirstWebShop.scanFiles()
        
        println "-----------------------------------"

        context.myFirstWebShop.scanFiles()
    }
}
