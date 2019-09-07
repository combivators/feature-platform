package net.tiny.feature.assess;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class FormPolicyTest {


    @Test
    public void testIllegalArgument() throws Exception {
        try {
            new Form.Balance(Types.Type.Skill.name, 1.2d);
            fail();
        }catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
            assertEquals("'Skill' balance weight '1.20' must be less than 0.99", e.getMessage());
        }
    }

    @Test
    public void testApllicationTypes() throws Exception {
        assertTrue(Types.Type.Compatibility.equals(Types.Type.Compatibility));
        assertFalse(Types.Type.Management.equals(Types.Type.Compatibility));
    }

    @Test
    public void testJsonForm() throws Exception {

        Form.Balance b1 = new Form.Balance(Types.Type.Skill.name, 0.02d);
        Form.Balance b2 = new Form.Balance(Types.Type.Learning.name, 0.12d);
        Form.Balance b3 = new Form.Balance(Types.Type.Management.name, 0.23d);
        Form.Balance b4 = new Form.Balance(Types.Type.Productivity.name, 0.03d);
        Form.Balance b5 = new Form.Balance(Types.Type.Growing.name, 0.34d);
        Form.Balance b6 = new Form.Balance(Types.Type.Compatibility.name, 0.4d);
        assertTrue(b6.isValid());

        List<Form.Balance> balances = new ArrayList<>();
        balances.addAll(Arrays.asList(b1,b2,b3,b4,b5,b6));
        Form.Policy visa = new Form.Policy();
        visa.name = "Visa";
        visa.balances = balances;
        Form.Policy education = Types.generatePolicy("Education", new double[] {0.01d, 0.02d, 0.03d, 0.00d, 0.05d, 0.06d});
        Form.Policy years = Types.generatePolicy("Years", new double[] {0.1d, 0.2d, 0.3d});

        assertTrue(education.hasBalance(Types.Type.Compatibility.name));
        assertFalse(education.hasBalance(Types.Type.Productivity.name));
        assertFalse(years.hasBalance(Types.Type.Compatibility.name));

        Form form = new Form();
        form.id = "0";
        form.modified = System.currentTimeMillis();
        form.expires = System.currentTimeMillis();
        form.signature = "Hoge";
        form.policies = Arrays.asList(visa, education, years);

        String json = JsonParser.marshal(form);
        System.out.print(json);
    }

    @Test
    public void testLoadResouces() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/form.json"));
        Form form = JsonParser.unmarshal(reader, Form .class);
        assertNotNull(form);
        reader.close();

        Form.Policy education = form.getPolicy("Education").get();
        assertTrue(education.hasBalance(Types.Type.Compatibility.name));
        assertTrue(education.hasBalance(Types.Type.Productivity.name));

        assertTrue(form.getPolicy("Duties").isPresent());

        String json = JsonParser.marshal(form);
        System.out.println(json);
    }

    @Test
    public void testApplyForm() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/form-source.json"));
        Form source = JsonParser.unmarshal(reader, Form .class);
        assertNotNull(source);
        reader.close();
        assertEquals(0.0d, source.getWeight("Id", "Compatibility"));
        assertEquals(0.16d, source.getWeight("Language", "Compatibility"));
        assertFalse(source.getBalance("Age", "Learning").isPresent());

        reader = new FileReader(new File("src/test/resources/data/form-revision.json"));
        Form revision = JsonParser.unmarshal(reader, Form .class);
        assertNotNull(revision);
        reader.close();

        assertEquals(0.5d, revision.getWeight("Id", "Compatibility"));

        Form after = source.apply(revision);
        assertSame(source, after);
        assertEquals(0.5d, after.getWeight("Id", "Compatibility"));
        assertEquals(0.26d, after.getWeight("Language", "Compatibility"));
        assertTrue(after.getBalance("Age", "Learning").isPresent());
        assertEquals(0.02d, after.getWeight("Age", "Learning"));

        String json = JsonParser.marshal(after);
        System.out.println(json);
        System.out.println("-----------------");

        Form.Tables tables = after.toTables();
        json = JsonParser.marshal(tables);
        System.out.println(json);
    }
}
