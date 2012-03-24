environments {
    development {
        dsl {
            emcInheritance = true
            //scripts = 'src/test/scripts'
            evaluate = ["evaluate", "include"]
            mbSchemaFiles = ["src/main/conf/FeatureModelSchema.groovy"]
            delegates = [[dslKey: "FeatureModel", clazz: org.beedom.dslforge.integrations.MetaBuilderDelegate],
                         org.beedom.beedriven.delegates.ScenarioDelegate,
                         org.beedom.beedriven.delegates.FeatureDelegate]
        }

        beedriven {
            reportDir = 'build/reports'
        }
    }
}
