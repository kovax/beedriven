package org.beedom.beedriven.model

import java.io.File;

class ScenarioRef {
    
    /**
     * The name of the Scenario
     * Must be identcal with the Scenario file name (without exetension) 
     * or with name of the Scenario inside of the Feature file
     * 
     * TODO: aply rule to accept characters which are valid for file namea
     */
    String name = ""

    
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
     * 
     * @param name
     */
    public void createScenario(String name) {
    }

    
    /**
     * 
     * @param name
     * @return
     */
    public File createScenarioFile(String name) {
    }
}
