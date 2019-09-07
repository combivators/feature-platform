package net.tiny.feature.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;
import net.tiny.config.Mapper;

public class UsersTest {

	@Test
    public void testJsonSet() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/users.json"));
        Object obj = new JsonParser().parse(reader);
        assertTrue(obj instanceof List);
        List<?> list = (List<?>)obj;
        assertEquals(10, list.size());
        assertTrue(list.get(0) instanceof Map);

        Mapper mapper = new Mapper();
        List<Users.User> users = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
        	Users.User user = mapper.convert((Map<String, ?>)list.get(i), Users.User.class);
        	users.add(user);
        }
        assertEquals(1, users.get(0).id);
		reader.close();
	}

	@Test
    public void testListJson() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/users.json"));
        List<Users.User> users = JsonParser.unmarshals(reader, Users.User.class);
		reader.close();
        assertEquals(10, users.size());
        assertEquals(1, users.get(0).id);
	}

}
