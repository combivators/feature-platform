package net.tiny.feature.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class UploadedTest {

    static final String LF = System.getProperty("line.separator");
    @Test
    public void testJson() throws Exception {
        Uploaded uploaded = new Uploaded();
        String json = JsonParser.marshal(uploaded);
        System.out.println(json);
        String expected = "{" + LF +
                "  \"error\" : \"\"," + LF +
                "  \"errorkeys\" : []," + LF +
                "  \"preview\" : []," + LF +
                "  \"append\" : \"true\"" + LF +
                "}" + LF;
        assertEquals(expected, json);
    }

}
