package net.tiny.feature.svg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import net.tiny.ws.BaseWebService;
import net.tiny.ws.HttpDateFormat;
import net.tiny.ws.HttpHandlerHelper;
import net.tiny.ws.RequestHelper;
import net.tiny.ws.ResponseHeaderHelper;

/**
 * 输出fontawesome格式图标
 * API: /v1/svg/{fas/fa-icon/fillColor/gbColor}
 */
public class BrandImageService extends BaseWebService {
    private String fontawesome = "/home/fontawesome-free-5.11.2-web";
    private long maxAge  = 86400L; //1 day

    private Map<String, String> index = new HashMap<>();
    private Map<String, String> colors = new HashMap<>();

    public BrandImageService() {
        index.put("fa", "solid");
        index.put("fas", "solid");
        index.put("fab", "brnds");
        index.put("far", "regular");
        Collections.unmodifiableMap(index);

        //Bootstrap colors
        colors.put("primary", "#007bff");
        colors.put("secondary", "#6c757d");
        colors.put("success", "#28a745");
        colors.put("info", "#17a2b8");
        colors.put("muted", "solid");
        colors.put("warning", "#ffc107");
        colors.put("danger", "#dc3545");
        colors.put("light", "#f8f9fa");
        colors.put("dark", "#343a40");
        colors.put("white", "#fff");
        colors.put("blue", "#007bff");
        colors.put("indigo", "#6610f2");
        colors.put("purple", "#6f42c1");
        colors.put("pink", "#e83e8c");
        colors.put("red", "#dc3545");
        colors.put("orange", "#fd7e14");
        colors.put("yellow", "#ffc107");
        colors.put("green", "#28a745");
        colors.put("teal", "#20c997");
        colors.put("cyan", "#17a2b8");
        colors.put("gray", "#6c757d");
        colors.put("gray-dark", "#343a40");
        Collections.unmodifiableMap(colors);
    }

    @Override
    protected boolean doGetOnly() {
        return true;
    }

    @Override
    protected void execute(HTTP_METHOD method, HttpExchange he) throws IOException {
        final RequestHelper request = HttpHandlerHelper.getRequestHelper(he);
        final String[] ids = request.getAllParameters();
        final ResponseHeaderHelper header = HttpHandlerHelper.getHeaderHelper(he);
        try {
            byte[] svg = generateSvg(ids);
            header.setContentType(MIME_TYPE.SVG);
            header.set(HEADER_LAST_MODIFIED, HttpDateFormat.format(new Date(System.currentTimeMillis())));
            header.set("Server", DEFALUT_SERVER_NAME);
            header.set("Connection", "Keep-Alive");
            header.set("Keep-Alive", "timeout=10, max=1000");
            if (maxAge > 0L) {
              header.set("Cache-Control", "max-age=" + maxAge); //"max-age=0" 86400:1day
            }
            header.setContentLength(svg.length);
            he.sendResponseHeaders(HttpURLConnection.HTTP_OK, svg.length);
            he.getResponseBody().write(svg);
        } catch (Exception e) {
            //Not found
            he.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, -1);
        }
    }

    // Generate scores charts. Max 4 id.
    byte[] generateSvg(String... fas) {
        try {
            final StringBuilder resource = new StringBuilder(fontawesome);
            resource.append("/svgs/");
            resource.append(index.get(fas[0]));  //fas:solid fab:brnds far:regular
            resource.append("/");
            resource.append(fas[1].substring(3));
            resource.append(".svg");
            byte[] template = super.readResource(resource.toString());
            switch(fas.length) {
            case 3:
                template = setStyle(template, fas[2], null);
                break;
            case 4:
                template = setStyle(template, fas[2], fas[3]);
                break;
            default:
                break;
            }
            return template;

        } catch (Exception ex) {
            try {
                return super.readResource(fontawesome + "/svgs/solid/question.svg");
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    //<svg xmlns="http://www.w3.org/2000/svg" style="background-color:#ccc">
    //<path style="fill:red;fill-opacity:1">
    byte[] setStyle(byte[] buffer, String fill, String bgcolor) {
        StringBuffer sb = new StringBuffer(new String(buffer));
        int pos;
        String style;
        if (bgcolor != null) {
            pos = sb.indexOf("<svg");
            pos = sb.indexOf(">", (pos+5));
            style = String.format(" style=\"background-color:%s\"", color(bgcolor));
            sb.insert(pos, style);
        }
        if (fill != null) {
            pos = sb.indexOf("<path");
            pos = sb.indexOf(">", (pos+6));
            style = String.format(" style=\"fill:%s\"", color(fill));
            sb.insert(pos-1, style);
        }
        return sb.toString().getBytes();
    }

    String color(String c) {
        String sc = colors.get(c);
        return sc != null ? sc : c;
    }
}
