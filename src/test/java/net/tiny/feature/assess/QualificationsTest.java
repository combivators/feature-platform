package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class QualificationsTest {

    @Test
    public void testSampleQualifications() throws Exception {

        Reader reader = new FileReader(new File("src/test/resources/data/assets.json"));
        Assets assets = JsonParser.unmarshal(reader, Assets.class);
        reader.close();
        assertNotNull(assets);
        Assets.Qualifications qualifications = new Assets.Qualifications();
        qualifications.id = assets.id;
        qualifications.signature = assets.signature;
        qualifications.titles = assets.titles();
        qualifications.scores = assets.scores();


        assertEquals(18, qualifications.titles.length);
        assertEquals(18, qualifications.scores.length);
        assertEquals("Age", qualifications.titles[2]);
        assertEquals(52.0d, qualifications.scores[2]);

        String json = JsonParser.marshal(qualifications);
        System.out.print(json);


        reader = new FileReader(new File("src/test/resources/data/qualifications-sample.json"));
        Assets.Qualifications other = JsonParser.unmarshal(reader, Assets.Qualifications.class);

        assertEquals(18, other.scores.length);
        assertNull(qualifications.orders);
        assertEquals("Age", other.titles[2]);
        assertEquals(52.0d, other.scores[2]);

        reader.close();
        assertNotNull(other);
    }

    @Test
    public void testQualificationsWithOrders() throws Exception {

        Reader reader = new FileReader(new File("src/test/resources/data/assets.json"));
        Assets assets = JsonParser.unmarshal(reader, Assets.class);
        reader.close();
        assertNotNull(assets);
        Assets.Qualifications qualifications = new Assets.Qualifications();
        qualifications.id = assets.id;
        qualifications.signature = assets.signature;
        qualifications.orders = Types.getTitleOrders(assets.titles());
        qualifications.scores = assets.scores();


        assertNull(qualifications.titles);
        assertEquals(18, qualifications.orders.length);
        assertEquals(18, qualifications.scores.length);
        assertEquals(2, qualifications.orders[2]);
        assertEquals(52.0d, qualifications.scores[2]);

        String json = JsonParser.marshal(qualifications);
        System.out.print(json);


        reader = new FileReader(new File("src/test/resources/data/qualifications-orders.json"));
        Assets.Qualifications other = JsonParser.unmarshal(reader, Assets.Qualifications.class);
        assertNotNull(other);
        assertEquals(18, other.scores.length);
        assertNull(other.titles);
        assertEquals("1.0", other.ver);
        assertEquals(52.0d, other.scores[2]);
        assertEquals(2, other.orders[2]);

        reader.close();

    }
}
