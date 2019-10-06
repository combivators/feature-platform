package net.tiny.feature.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.tiny.config.JsonParser;

public class UsersLoaderTest {

	@Test
    public void testLoadUsers() throws Exception {
		UsersLoader usersLoader = new UsersLoader();
    	Users users = usersLoader.get();
    	assertNotNull(users);
    	String json = JsonParser.marshal(users.users);
    	assertNotNull(json);
    	System.out.println(json);
	}


	@Test
    public void testLoadResouces() throws Exception {
        Reader reader = new FileReader(new File("src/test/resources/data/users.json"));
        List<Users.User> list = JsonParser.unmarshals(reader, Users.User.class);
        assertNotNull(list);
        assertEquals(10, list.size());
        reader.close();
        String json = JsonParser.marshal(list);
        System.out.println(json);
	}
}
