mbObjectKeys = [
    //(org.beedom.beedriven.model.FeatureRef) : {f-> "${f.name}_${f.version}"},
    (org.beedom.beedriven.model.FeatureRef)   : "name",
    (org.beedom.beedriven.model.ScenarioRef)  : "name",
    (org.beedom.beedriven.model.FeatureModel) : "name"
    ]

metaBuilder.define {
    FeatureModelElement(factory: org.beedom.beedriven.model.FeatureModelElement) {
        properties {
            name()
        }
    }
    
    ScenarioRef(schema: 'FeatureModelElement', factory: org.beedom.beedriven.model.ScenarioRef) {
        properties {
            implemented()
            automated()
            executed()
            failed()
        }
    }

    FeatureRef(schema: 'FeatureModelElement', factory: org.beedom.beedriven.model.FeatureRef) {
        properties {
        }

        collections {
            scenarios(key:'name') {
                ScenarioRef(schema: "ScenarioRef")
            }
            
            optional(key:'name') {
                FeatureRef(schema: "FeatureRef")
            }
            mandatory(key:'name') {
                FeatureRef(schema: "FeatureRef")
            }
            alternative(key:'name') {
                FeatureRef(schema: "FeatureRef")
            }
            or(key:'name') {
                FeatureRef(schema: "FeatureRef")
            }
        }
    }
    
    ProductRef(schema: 'FeatureRef', factory: org.beedom.beedriven.model.FeatureModel) {
        properties {
            version()
        }
    }
}
