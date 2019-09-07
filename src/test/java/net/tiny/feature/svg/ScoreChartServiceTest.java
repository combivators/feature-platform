package net.tiny.feature.svg;

import static org.junit.jupiter.api.Assertions.*;

import java.io.LineNumberReader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

public class ScoreChartServiceTest {


    @Test
    public void testGeneratePolygon() throws Exception {
        ScoreChartService servie = new ScoreChartService();
        assertNotNull(servie);
        float[] s1 = {25.0f, 50.0f, 75.0f, 100.0f, 75.0f, 50.0f};
        float[] s2 = {82.26f, 83.45f, 84.64f, 85.83f, 87.02f, 88.21f};
        float[] s3 = {0.25f, 0.75f, 1.00f, 0.50f, 0.60f, 0.45f};
        float[][] scores = {s1, s2, s3};
        String polygons = servie.generatePolygons(scores);
        LineNumberReader reader = new LineNumberReader(new StringReader(polygons));
        String p1 = reader.readLine();
        String p2 = reader.readLine();
        String p3 = reader.readLine();
        assertTrue(p1.contains("cs1"));
        assertTrue(p2.contains("cs2"));
        assertTrue(p3.contains("cs3"));

        assertTrue(p1.contains("0.00,-75.00 129.90,-75.00 194.86,112.50 0.00,300.00 -194.86,112.50 -129.90,-75.00"));
        assertTrue(p2.contains("0.00,-246.78 216.81,-125.17 219.90,126.96 0.00,257.49 -226.08,130.53 -229.18,-132.32"));
        assertTrue(p3.contains("0.00,-0.75 1.95,-1.12 2.60,1.50 0.00,1.50 -1.56,0.90 -1.17,-0.67"));
    }

    @Test
    public void testGenerateChart() throws Exception {
        ScoreChartService servie = new ScoreChartService();
        assertNotNull(servie);
        String id1 = "CwoqFQArtoRdNXYJMdpzv3lCepiTDrAt8TFYI4";
        String id2 = "CwoqKr8hmIy2QxOHU8euCBU0M7jA77JkTBchMW";
        String id3 = "CxjsGYYcPZfHl2vJpaMFlxYiNH3WDzpx8eVr7t";
        String id4 = "CwotWpW7cKAX1s2Lj6ZbPyOqzcoEghF1qv1WkP";
        byte[] svg = servie.generateChart(id1, id2, id3, id4);
        System.out.println(new String(svg));
    }
}
