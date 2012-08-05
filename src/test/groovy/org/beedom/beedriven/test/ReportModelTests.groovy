package org.beedom.beedriven.test

import org.junit.Before;

import org.beedom.dslforge.SimpleRenderer;
import org.beedom.dslforge.SimpleRenderer.ReportType;

import org.junit.Test


class ReportModelTests extends TestBase {
    
    def root = "build/reports/beedriven/src/test/scripts"

    @Before
    public void init() {
        super.init()
        
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))
    }

    @Test
    public void executeOneFeature() {
        ReportType type = ReportType.XML
        context.myFirstWebShop.generateReport(type)

        def f = new File("$root/CustomerSelfManagement/ShippingManagement/ShippingManagement."+SimpleRenderer.getFileExt(type))

        assert f.exists()
        assert f.grep(~/Shipping/).size() > 0
    }
}
