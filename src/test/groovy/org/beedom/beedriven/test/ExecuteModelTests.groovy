package org.beedom.beedriven.test

import org.beedom.beedriven.model.FeatureModelElement.Type
import org.junit.Test

class ExecuteModelTests extends TestBase {

    @Test
    public void executeOneFeature() {
        dsle.run("WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.execute(deep: false, dry: false, isolated: false)

        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        //TODO: test Feature was run only once - HOW to test it??? - probably check generated report

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
    }

    @Test
    public void executeOneFeatureIsolated() {
        dsle.run("WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.execute(deep: false, dry: false, isolated: true)

        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        //TODO: test Feature was run for each scenario - HOW to test it??? - probably check generated report

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
    }

    @Test
    public void executeFeaturesIsolatedDeep() {
        dsle.run("WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.execute(deep: true, isolated: true)

        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        //TODO: test Feature was run for each scenario - HOW to test it??? - probably check generated report

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
    }
}
