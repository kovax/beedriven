package org.beedom.beedriven.test


import org.junit.Test

class FeatureModelTests extends TestBase {

	@Test
	public void runModel() {
		dsle.run("WebShop.fm")

        assert context.myFirstWebShop
        assert context.myFirstWebShop.optional[0]
        assert context.myFirstWebShop.mandatory[0]
	}
}
