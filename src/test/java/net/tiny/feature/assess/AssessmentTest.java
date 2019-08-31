package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssessmentTest {

    @Test
    public void testGenerateScoresId() throws Exception {
        float[] values = new float[] {25.0f, 75.0f, 98.f, 50.0f, 60.23f, 45.00f};
        String id  = Assessment.generateScoresId(1, values);
        assertEquals("CwoqFQArtoRdNXYJMdpzv3lCepiTDrAt8TFYI4", id);

        values = new float[] {25.0f, 75.0f, 50.0f, 95f, 75.0f, 50.0f};
        id  = Assessment.generateScoresId(123, values);
        assertEquals("CwoqKr8hmIy2QxOHU8euCBU0M7jA77JkTBchMW", id);

        values = new float[] {82.26f, 83.45f, 84.64f, 85.83f, 87.02f, 88.21f};
        id  = Assessment.generateScoresId(4567, values);
        assertEquals("CwotWpW7cKAX1s2Lj6ZbPyOqzcoEghF1qv1WkP", id);
    }
}
