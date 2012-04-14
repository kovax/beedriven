package org.beedom.beedriven.test

import org.beedom.beedriven.model.FeatureModelElement.Type
import org.beedom.dslforge.SimpleRenderer;
import org.junit.Test

class ExecuteModelTests extends TestBase {

    @Test
    public void executeOneFeature() {
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.execute(deep: false, dry: false, isolated: false)

        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        //TODO: test Feature was run only once - HOW to test it??? - probably check generated report

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
    }

    @Test
    public void executeOneFeatureIsolated() {
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.execute(deep: false, dry: false, isolated: true)

        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        //TODO: test Feature was run for each scenario - HOW to test it??? - probably check generated report

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
    }

    @Test
    public void executeFeaturesIsolatedDeep() {
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        context.myFirstWebShop.optional.CustomerSelfManagement.execute(deep: true, dry: false, isolated: true, report: SimpleRenderer.ReportType.XML)

        //TODO: test Feature was run for each scenario - HOW to test it??? - probably check generated report
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.executed
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.WebPageManagement.scenarios.EditWebPage.executed

        assert !context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement.scenarios.SetDefaultShippingAddress.implemented
        
        def f = new File("build/reports/src/test/scripts/CustomerSelfManagement/ShippingManagement/ShippingManagement.txt")
        assert f.exists()
        assert f.grep(~/Shipping/)
    }

}
