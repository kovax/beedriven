mbObjectKeys = [
    //(org.beedom.beedriven.model.FeatureRef) : {f-> "${f.name}_${f.version}"},
    (org.beedom.beedriven.model.FeatureFile)  : "name",
    (org.beedom.beedriven.model.ScenarioFile) : "name",
    (org.beedom.beedriven.model.FeatureModel) : "name"
    ]

metaBuilder.define {
    FeatureModelElement(factory: org.beedom.beedriven.model.FeatureModelElement) {
        properties {
            name()
            selected()
        }
    }
    
    ScenarioFile(schema: 'FeatureModelElement', factory: org.beedom.beedriven.model.ScenarioFile) {
        properties {
            implemented()
            automated()
            executed()
            failed()
        }
    }

    FeatureFile(schema: 'FeatureModelElement', factory: org.beedom.beedriven.model.FeatureFile) {
        properties {
        }

        collections {
            scenarios(key:'name') {
                ScenarioFile(schema: "ScenarioFile")
            }
            
            optional(key:'name') {
                FeatureFile(schema: "FeatureFile")
            }
            mandatory(key:'name') {
                FeatureFile(schema: "FeatureFile")
            }
            alternative(key:'name') {
                FeatureFile(schema: "FeatureFile")
            }
            or(key:'name') {
                FeatureFile(schema: "FeatureFile")
            }
        }
    }
    
    ProductFile(schema: 'FeatureFile', factory: org.beedom.beedriven.model.FeatureModel) {
        properties {
            version()
        }
    }
}
