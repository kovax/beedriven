package org.beedom.beedriven.test


import org.beedom.beedriven.model.FeatureModelElement;
import org.junit.Before;
import org.junit.Test
import org.beedom.beedriven.model.FeatureModelElement.Type
import org.beedom.beedriven.model.FeatureFile
import org.beedom.beedriven.model.ScenarioFile



class FeatureModelTests extends TestBase {
    
	@Test
	public void loadModel() {
        dsle.run("src/test/scripts/WebShop.fm")
        
        assert context.myFirstWebShop
        assert context.myFirstWebShop.optional.CustomerSelfManagement
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Login
	}
    
    @Test
    public void scanRoot() {
        dsle.run("src/test/scripts/WebShop.fm")

        assert !context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Registration
        
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))

        assert context.myFirstWebShop
        assert context.myFirstWebShop.optional.CustomerSelfManagement
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Login
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Logout
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.Registration
        assert context.myFirstWebShop.mandatory.Security.mandatory.CustomerAuthentication.scenarios.ForgotPassword
        
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.ShippingManagement
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.AddressManagement
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.EmailManagement
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.CreditCardManagement
        assert context.myFirstWebShop.optional.CustomerSelfManagement.mandatory.WebPageManagement
        
        //traverse all features and scenarios
        context.myFirstWebShop.traverse { String type, modelElement -> 
            //println modelElement.dslFile
            if(modelElement.name == "ForgottenPassword") {
                assert !modelElement.dslFile //There is no ForgottenPassword.scenario
            }
            else {
                assert modelElement.dslFile
            }
        }
    }

    @Test
    public void traverseFeatures() {
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))
        
        context.myFirstWebShop.traverse( type: Type.FEATURE ) { String type, modelElement ->
            assert modelElement
            assert modelElement instanceof FeatureFile
            assert modelElement.dslFile
            assert modelElement.dslFile.name.endsWith(".feature")
        }
    }

    @Test
    public void traverseScenarios() {
        dsle.run("src/test/scripts/WebShop.fm")
        context.myFirstWebShop.scanFiles(new File("src/test/scripts"))
        
        context.myFirstWebShop.traverse( type: Type.SCENARIO ) { String type, modelElement ->
            assert modelElement
            assert modelElement instanceof ScenarioFile

            if(modelElement.name == "ForgottenPassword") {
                assert !modelElement.dslFile //There is no ForgottenPassword.scenario
            }
            else {
                assert modelElement.dslFile
                assert modelElement.dslFile.name.endsWith(".scenario")
            }
        }
    }
}
