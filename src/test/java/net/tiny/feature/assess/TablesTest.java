package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class TablesTest {

    @Test
    public void testJsonTables() throws Exception {
        Form.Tables tables = new Form.Tables();
        tables.id = "0";
        tables.signature = "Hoge";

        Form.Table t1 = new Form.Table();
        t1.title = "Nationality";
        t1.weights = new double[] {0.01d, 0.02d, 0.03d, 0.00d, 0.05d, 0.06d};
        Form.Table t2 = new Form.Table();
        t2.title = "Education";
        t2.weights = new double[] {0.11d, 0.22d, 0.33d, 0.04d, 0.15d, 0.16d};
        tables.tables = Arrays.asList(t1, t2);
        tables.types = Arrays.asList("Skill", "Learning", "Management", "Productivity", "Growing", "Compatibility");
        String json = JsonParser.marshal(tables);
        System.out.print(json);

        Matrix<Double> matrix = tables.matrix();
        assertNotNull(matrix);

        Reader reader = new FileReader(new File("src/test/resources/data/tables.json"));
        Form.Tables data = JsonParser.unmarshal(reader, Form.Tables.class);
        assertNotNull(data);
        reader.close();
    }

    @Test
    public void testSampleTables() throws Exception {
        Form.Tables tables = new Form.Tables();
        tables.id = "0";
        tables.signature = "Hoge";

        tables.tables = new ArrayList<>();
        Form.Table tab;
        for (Types.Title title : Types.Title.values()) {
            tab = new Form.Table();
            tab.title = title.name();
            switch (title) {
            case Id:
                tab.weights = new double[] {0.00d, 0.00d, 0.00d, 0.00d, 0.00d, 0.00d};
                break;
            case Sex:
                tab.weights = new double[] {0.01d, 0.01d, 0.01d, 0.01d, 0.01d, 0.01d};
                break;
            case Age:
                tab.weights = new double[] {0.02d, 0.02d, 0.02d, 0.02d, 0.02d, 0.02d};
                break;
            case Nationality:
                tab.weights = new double[] {0.03d, 0.03d, 0.03d, 0.03d, 0.03d, 0.03d};
                break;
            default:
                tab.weights = new double[] {0.11d, 0.22d, 0.33d, 0.04d, 0.15d, 0.16d};
                break;
            }
            tables.tables.add(tab);
        }
        tables.types = Arrays.asList("Skill", "Learning", "Management", "Productivity", "Growing", "Compatibility");

        String json = JsonParser.marshal(tables);
        System.out.print(json);

        Reader reader = new FileReader(new File("src/test/resources/data/tables.json"));
        Form.Tables data = JsonParser.unmarshal(reader, Form.Tables.class);
        reader.close();
        assertNotNull(data);
        Matrix<Double> matrix = data.matrix();
        assertNotNull(matrix);
        assertEquals(18, matrix.getHeight());
        assertEquals(6, matrix.getWidth());
    }

    @Test
    public void testFormToTables() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/form.json"));
        Form form = JsonParser.unmarshal(reader, Form.class);
        reader.close();
        assertNotNull(form);
        List<String> types = form.getTypes();
        assertEquals(6, types.size());

        Map<String, Double> weights = form.weights(Types.Type.Skill.name());
        assertEquals(18, weights.size());
        assertEquals(0.0d, weights.get("Id"));
        assertEquals(0.11d, weights.get("Language"));
        assertEquals(0.13d, weights.get("Experience"));

        Form.Tables tables = form.toTables();
        assertNotNull(form);
        String json = JsonParser.marshal(form);
        System.out.print(json);

        Matrix<Double> matrix = tables.matrix();
        System.out.println("-------- Tables Matrix --------");
        matrix.print(System.out);
        System.out.println("-------------------------------");

        Matrix<Double> other = matrix.transpose();
        System.out.println("------- Transpose Matrix ------");
        other.print(System.out);
        System.out.println("-------------------------------");


        reader = new FileReader(new File("src/test/resources/data/assets.json"));
        Assets assets = JsonParser.unmarshal(reader, Assets.class);
        reader.close();
        assertNotNull(assets);
        Double[] scores = assets.scores();
        assertEquals(18, scores.length);
        Double s = assets.score("Experience");
        assertEquals(64.0d, s);
    }

}
