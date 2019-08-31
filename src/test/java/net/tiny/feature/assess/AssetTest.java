package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class AssetTest {

    @Test
    public void testSampleAssets() throws Exception {
        Assets assets = new Assets();
        assets.id = "0";
        assets.signature = "Hoge";

        assets.assets = new ArrayList<>();
        Assets.Asset as;
        for (Types.Title title : Types.Title.values()) {
            as = new Assets.Asset();
            as.name = title.name();
            if (Types.Title.Id.equals(title)) {
                as.score = 0.0d;
            } else {
                as.score = 50.00d;
            }
            assets.assets.add(as);
        }
        String json = JsonParser.marshal(assets);
        System.out.print(json);

        Reader reader = new FileReader(new File("src/test/resources/data/assets.json"));
        Assets data = JsonParser.unmarshal(reader, Assets.class);
        reader.close();
        assertNotNull(data);
        Double[] scores = data.scores();
        assertEquals(18, scores.length);

        assertFalse(data.vaild("Id"));
        assertTrue(data.vaild("Visa"));
    }
}
