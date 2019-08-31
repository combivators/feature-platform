package net.tiny.feature.svg;

import java.text.DecimalFormat;

public class ChartPaths {

    public static float[][] calculatePoints(final float[] percents, final float radius) {
        final int size = percents.length;
        final float a = 360.f / size;
        final float[][] points = new float[size][2];
        for (int i=0; i<size; i++) {
            float[] p = calculatePoint((percents[i] * radius), (float)(i * a - 90.0f)); // Clockwise
            points[i][0] = p[0];
            points[i][1] = p[1];
        }
        return points;
    }

    public static String getPolygonPoints(final float[] percents, final float radius) {
        final float[][] points = calculatePoints(percents, radius);
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<percents.length; i++) {
            if (sb.length() >0) {
                sb.append(" ");
            }
            sb.append(String.format("%.2f,%.2f", points[i][0], points[i][1]));
        }
        return sb.toString();
    }


    public static float[] calculatePoint(float radius, float angle) {
        final DecimalFormat format = new DecimalFormat("###.##");
        final float x = Float.parseFloat(format.format(Math.cos(Math.toRadians(angle)) * radius));
        final float y = Float.parseFloat(format.format(Math.sin(Math.toRadians(angle)) * radius));
        return new float[] {x, y};
    }
}
