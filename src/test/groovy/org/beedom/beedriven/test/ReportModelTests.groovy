package org.beedom.beedriven.test

import org.junit.Before;

import org.beedom.dslforge.SimpleRenderer;
import org.beedom.dslforge.SimpleRenderer.ReportType;

import org.junit.Test


class ReportModelTests extends TestBase {

    @Before
    public void init() {
        super.init()
        
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))
    }

    @Test
    public void executeOneFeature() {
        context.myFirstWebShop.generateReport(ReportType.TXT)

        def f = new File("build/reports/src/test/scripts/CustomerSelfManagement/ShippingManagement/ShippingManagement.txt")

        assert f.exists()
        assert f.grep(~/Shipping/)
    }
}
