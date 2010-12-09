mbObjectKeys = [
    (org.beedom.beedriven.Product) : {p-> "${p.name}_${p.version}"},
    (org.beedom.beedriven.Feature) : "name"
    ]

def bddFeatureSchema

bddFeatureContSchema = metaBuilder.define {
    fmContainer(factory: org.beedom.beedriven.FeatureContainer) {
        properties {
            name()
        }
        collections {
            optional {
                fmFeature(schema: bddFeatureSchema, factory: org.beedom.beedriven.Feature)
            }
            mandatory {
                featurett(schema: bddFeatureSchema, factory: org.beedom.beedriven.Feature)
            }
            alternative {
                featurett(schema: bddFeatureSchema, factory: org.beedom.beedriven.Feature)
            }
            or {
                featurett(schema: bddFeatureSchema, factory: org.beedom.beedriven.Feature)
            }
        }
    }
}


bddFeatureSchema = metaBuilder.define {
    fmFeature(schema: bddFeatureContSchema, factory: org.beedom.beedriven.Feature) {
        properties {
            description()
        }
    }
}

def bddProductSchema = metaBuilder.define {
    fmProduct(schema: bddFeatureContSchema, factory: org.beedom.beedriven.Product) {
        properties {
            version()
        }
    }
}
*/
