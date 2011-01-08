package org.beedom.beedriven.gradle

import org.beedom.dslforge.DSLEngine;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

class BeeDrivenPlugin implements Plugin<Project> {
    
    def void apply(Project project) {
        project.convention.plugins.beedriven = new BeeDrivenConvention()
        project.task('dsl') << {
            
            if(!project.convention.plugins.beedriven.dsl) {
                throw new InvalidUserDataException("No beedriven closure was found in script!")
            }

            def dsle = new DSLEngine(new Binding())
            dsle.run(project.convention.plugins.beedriven.dsl)
        }
    }
}
