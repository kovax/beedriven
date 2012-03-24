package org.beedom.beedriven.model

import java.util.Map;

import static org.beedom.beedriven.model.FeatureModelElement.Type.*

import org.beedom.dslforge.SimpleRenderer;
import org.beedom.dslforge.SimpleRenderer.ReportType;

import groovy.util.logging.Slf4j


/**
 * 
 * @author kovax
 *
 */
@Slf4j
class FeatureModel extends FeatureRef {

    protected static final templateName = "ProductRef"

    String version = ""

    public void execute() {
        execute([:])
    }

    public void execute(Map options) {
        setDefaultTraverseOptions(options)
        options.type = FEATURE

        log.debug "execute - options:$options"

        traverse( options ) { mapName, feature ->
            feature.execute(options)
        }
    }

    public void generateReport(ReportType type) {
        assert type, "Specify the type of the report"
        execute(deep: true, dry: true, isolated: false, report: type)
    }
}
