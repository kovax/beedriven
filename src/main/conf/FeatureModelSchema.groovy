mbObjectKeys = [
    //(org.beedom.beedriven.Feature) : {f-> "${f.name}_${f.version}"},
    (org.beedom.beedriven.Feature) : "name"
    ]

def bddFeatureSchema = metaBuilder.define {
    fmFeature(factory: org.beedom.beedriven.model.Feature) {
        properties {
            name()
            description()
            version()
        }
        collections {
            optional {
                fmFeature(schema: "fmFeature")
            }
            mandatory {
                fmFeature(schema: "fmFeature")
            }
            alternative {
                fmFeature(schema: "fmFeature")
            }
            or {
                fmFeature(schema: "fmFeature")
            }
        }
    }
}
