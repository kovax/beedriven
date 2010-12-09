environments {
     development {
         dsl.emcInheritance = true
         dsl.scripts = 'src/test/scripts'
         dsl.evaluate = ["evaluate", "include"]
         dsl.delegates = [org.beedom.beedriven.delegates.MetaBuilderDelegate,
                          org.beedom.beedriven.delegates.ScenarioDelegate,
                          org.beedom.beedriven.delegates.FeatureDelegate]
         //dsl.categories = [org.beedom.dslforge.test.decorators.ShallCategory]
     }
}
