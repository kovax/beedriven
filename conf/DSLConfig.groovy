environments {
     development {
         dsl.emcInheritance = true
         dsl.scripts = 'src/test/scripts'
         dsl.evaluate = ["evaluate", "include"]
         mbSchemaFiles = ["src/main/conf/MetaBuilderSchema.groovy"]
         dsl.delegates = [[dslKey: "FeatureModel", clazz: org.beedom.dslforge.integrations.MetaBuilderDelegate],
                          org.beedom.beedriven.delegates.ScenarioDelegate,
                          org.beedom.beedriven.delegates.FeatureDelegate]
     }
}
