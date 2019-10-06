package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class AppraiserTest {

    @Test
    public void testAssessForm() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/form.json"));
        Form form = JsonParser.unmarshal(reader, Form.class);
        reader.close();
        assertNotNull(form);

        reader = new FileReader(new File("src/test/resources/data/assets.json"));
        Assets assets = JsonParser.unmarshal(reader, Assets.class);
        reader.close();
        assertNotNull(assets);

        Appraiser appraiser = new Appraiser();

        Assessment assessment = appraiser.apply(form, assets);
        assertNotNull(assessment);

        String json = JsonParser.marshal(assessment);
        System.out.print(json);

        Map<String,Double> summary = assessment.summary(Types.getCatalog());
        System.out.println(summary);

        Assessment.Scores scores = assessment.toScores();
        json = JsonParser.marshal(scores);
        System.out.println(json);
    }

    @Test
    public void testAssessTables() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/tables.json"));
        Form.Tables tables = JsonParser.unmarshal(reader, Form.Tables.class);
        reader.close();
        assertNotNull(tables);
        assertNotNull(tables.types);
        assertEquals(6, tables.types.size());

        reader = new FileReader(new File("src/test/resources/data/qualifications-sample.json"));
        Assets.Qualifications qualifications = JsonParser.unmarshal(reader, Assets.Qualifications.class);
        reader.close();
        assertNotNull(qualifications);

        Appraiser appraiser = new Appraiser();

        Assessment assessment = appraiser.assess(tables, qualifications);
        assertNotNull(assessment);

        String json = JsonParser.marshal(assessment);
        System.out.print(json);

        Map<String,Double> summary = assessment.summary(Types.getCatalog());
        System.out.println(summary);

        Assessment.Scores scores = assessment.toScores();
        json = JsonParser.marshal(scores);
        System.out.println(json);
    }
}
