package net.tiny.feature.svg;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChartPathsTest {

    @Test
    public void testCalculatePoint() throws Exception {
        assertNotEquals(0.0f, -0.0f);

        float[] points  = ChartPaths.calculatePoint(0.0f, 0.0f);
        assertEquals(2, points.length);
        assertEquals(0.0f, points[0]);
        assertEquals(0.0f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 45.0f);
        assertEquals(2, points.length);
        assertEquals(70.71f, points[0]);
        assertEquals(70.71f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 90.0f);
        assertEquals(2, points.length);
        assertEquals(0.0f, points[0]);
        assertEquals(100.0f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 180.0f);
        assertEquals(2, points.length);
        assertEquals(-100.0f, points[0]);
        assertEquals(0.0f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 225.0f);
        assertEquals(2, points.length);
        assertEquals(-70.71f, points[0]);
        assertEquals(-70.71f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 270.0f);
        assertEquals(2, points.length);
        assertEquals(-0.0f, points[0]);
        assertEquals(-100.0f, points[1]);

        points  = ChartPaths.calculatePoint(100.0f, 315.0f);
        assertEquals(2, points.length);
        assertEquals(70.71f, points[0]);
        assertEquals(-70.71f, points[1]);
    }

    @Test
    public void testGetPolygonPoints() throws Exception {
        float[] percents = new float[] {0.25f, 0.75f, 1.00f, 0.50f, 0.60f, 0.45f}; //顺时针
        String polygon  = ChartPaths.getPolygonPoints(percents, 300.0f);
        assertEquals("0.00,-75.00 194.86,-112.50 259.81,150.00 0.00,150.00 -155.88,90.00 -116.91,-67.50",
                polygon);
    }

    @Test
    public void testCalculatePercentPoints() throws Exception {
        float[] percents = new float[] {0.25f, 0.75f, 0.50f, 1.0f, 0.75f, 0.50f}; //顺时针
        String polygon  = ChartPaths.getPolygonPoints(percents, 100.0f);
        assertEquals("0.00,-25.00 64.95,-37.50 43.30,25.00 0.00,100.00 -64.95,37.50 -43.30,-25.00",
                polygon);
    }
}
