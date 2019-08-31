package net.tiny.feature.svg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;

import net.tiny.ws.BaseWebService;
import net.tiny.ws.HttpDateFormat;
import net.tiny.ws.HttpHandlerHelper;
import net.tiny.ws.RequestHelper;
import net.tiny.ws.ResponseHeaderHelper;
import net.tiny.ws.auth.Codec;

/**
 *
 * 输出SVG格式的评价结果雷达图
 *
 */
//API: /v1/chart/svg/{id}
public class ScoreChartService extends BaseWebService {
    //TODO When resize to re-calculate point
    private final String template;
    private String width  = "25cm";
    private String height = "20cm";
    private String[] colors = {"#37a", "#f73", "#7a3", "#a37"};
    private float radius = 300.0f; //半径
    private float rate   = 0.01f;  //倍率
    private long maxAge  = 86400L; //1 day

    public ScoreChartService() {
        try {
            template = loadResource("chart-spider.svg");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    protected boolean doGetOnly() {
        return true;
    }

    @Override
    protected void execute(HTTP_METHOD method, HttpExchange he) throws IOException {
        final RequestHelper request = HttpHandlerHelper.getRequestHelper(he);
        final ResponseHeaderHelper header = HttpHandlerHelper.getHeaderHelper(he);
        final String[] ids = request.getAllParameters();
        try {
            byte[] svg = generateChart(ids);
            header.setContentType(MIME_TYPE.SVG);
            header.set(HEADER_LAST_MODIFIED, HttpDateFormat.format(new Date(System.currentTimeMillis())));
            header.set("Server", DEFALUT_SERVER_NAME);
            header.set("Connection", "Keep-Alive");
            header.set("Keep-Alive", "timeout=10, max=1000");
            header.set("Cache-Control", "max-age=" + maxAge); //"max-age=0" 86400:1 day
            header.setContentLength(svg.length);
            he.sendResponseHeaders(HttpURLConnection.HTTP_OK, svg.length);
            he.getResponseBody().write(svg);
        } catch (Exception e) {
            //Not found
            he.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, -1);
        }
    }

    // Generate scores charts. Max 4 id.
    byte[] generateChart(String... ids) {
        final int size = ids.length;
        final float[][] scores = new float[size][6];
        for (int i=0; i<size; i++) {
            scores[i] = toScoresById(ids[i]);
        }
        return String.format(template, width, height,
                colors[0], colors[1], colors[2], colors[3],
                generatePolygons(scores),
                generateLabel(size)).getBytes();
    }

    // Convert id 'Cw...' to value array.
    float[] toScoresById(String id) {
        final ByteBuffer buffer = ByteBuffer.wrap(Codec.decodeString(id));
        final int capacity = buffer.capacity()/4 - 1;
        buffer.getInt(); //Skip Assessment ID
        final float[] scores = new float[capacity];
        for (int i=0; i<scores.length; i++) {
            scores[i] = buffer.getFloat();
        }
        return scores;
    }

    String generatePolygons(float[][] socres) {
        final String format = "            <polygon class=\"cs%d\" points=\"%s\" />\n";
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<socres.length; i++) {
            float[] percents = new float[socres[i].length];
            for (int n=0; n<percents.length; n++) {
                percents[n] = socres[i][n] * rate;
            }
            buffer.append(String.format(format, (i+1), ChartPaths.getPolygonPoints(percents, radius)));
        }
        return buffer.toString();
    }


    String generateCurves(float[][] socres) {
        //TODO
        return "<path class=\"cs3\"... />\n";
    }

    String generateLabel(int number) {
        final String format = "        <line class=\"cs%1$d\" x2=\"40\" y1=\"%2$d\" y2=\"%2$d\"/><text x=\"60\" y=\"%3$d\">%1$d</text>\n";
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<number; i++) {
            int y = i * 50;
            buffer.append(String.format(format, (i+1), y, (y+5)));
        }
        return buffer.toString();
    }
}
