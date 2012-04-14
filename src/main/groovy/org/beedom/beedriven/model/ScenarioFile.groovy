package org.beedom.beedriven.model

import groovy.util.logging.Slf4j

import java.io.File


@Slf4j
class ScenarioFile extends FeatureModelElement {

    /**
     * The implementation of the Scenario (consequently the Feature) was done or not
     * 
     * TODO: use an enum with values: UnImplemented/UnderDevelopment/Implemeneted
     */
    Boolean implemented = false


    /**
     * Can be run automatically or manually
     */
    Boolean automated = false


    /**
     * Whether it was run or not (automatically or manually)
     */
    Boolean executed = false


    /**
     * Whether it was successfully run or not
     * For unimplemented Scenarios this value is not relevant
     */
    Boolean failed = false


    /**
     * This is only for MetaBuilder support
     */
    ScenarioFile() {
    }


    /**
     * 
     */
    ScenarioFile(File f) {
        assert f && f.exists() && f.isFile(), "File must exists"
        dslFile = f
        name = getNameFromFile(f)
    }


    public void dumpToMetaBuilder(mbBuilder) {
    
    }

    /**
     * 
     * @param name
     */
    private void createScenario(String name) {
    }

    
    /**
     * 
     * @param name
     * @return
     */
    public File createScenarioFile(String name) {
    }
}
