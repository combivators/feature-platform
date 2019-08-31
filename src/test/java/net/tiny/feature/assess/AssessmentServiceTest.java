package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class AssessmentServiceTest {

    @Test
    public void testLoadTables() throws Exception {
        Form.Tables tables = AssessmentService.loadTables(1);
        assertNotNull(tables);
    }
}
