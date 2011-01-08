package org.beedom.beedriven.test


import org.junit.Test

class FeatureModelTests extends TestBase {

	@Test
	public void runModel() {
		dsle.run("WebShop.fm")

        assert context.myFirstWebShop
        assert context.myFirstWebShop.optional.'Customer Self Management'
        assert context.myFirstWebShop.mandatory['Security']
        assert context.myFirstWebShop.mandatory.Security.mandatory.'Customer Authentication'
        assert context.myFirstWebShop.mandatory.Security.mandatory.'Customer Authentication'.scenarios.Login
	}
}
