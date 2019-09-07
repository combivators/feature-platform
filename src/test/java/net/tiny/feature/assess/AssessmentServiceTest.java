package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * curl -u paas:password http://localhost:8080/sys/stop
 *
 * curl -X POST -H 'Content-Type:application/json' -H "Accept: application/json" http://localhost:8080/v1/api/assess/1/scores -d @src/test/resources/data/qualifications-sample.json
 *
 * curl http://localhost:8080/v1/chart/svg/CxjsGYYcPZfHl2vJpaMFlxYiNH3WDzpx8eVr7t
 *
 * curl http://localhost:8080/v1/chart/svg/CwoqKr8hmIy2QxOHU8euCBU0M7jA77JkTBchMW/CxjsGYYcPZfHl2vJpaMFlxYiNH3WDzpx8eVr7t/CwoqFQArtoRdNXYJMdpzv3lCepiTDrAt8TFYI4
 *
 */
public class AssessmentServiceTest {

    @Test
    public void testLoadTables() throws Exception {
        Form.Tables tables = AssessmentService.loadTables(1);
        assertNotNull(tables);
    }
}
