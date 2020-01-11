package net.tiny.feature.demo;

import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;
import java.util.function.Supplier;

import net.tiny.config.JsonParser;
import net.tiny.ws.client.SimpleClient;

public class UsersLoader implements Supplier<Users> {

	private Optional<Users> users;

	@Override
	public Users get() {
		return load().orElse(null);
	}

    public Optional<Users> load() {
    	if( users != null && users.isPresent()) {
    		return users;
    	}
        SimpleClient client = new SimpleClient.Builder()
                .build();
        try {
        	String response = client.doGet("http://jsonplaceholder.typicode.com/users");
            if(null != response && !response.isEmpty()) {
            	return Optional.of(new Users().addAll(JsonParser.unmarshals(new StringReader(response), Users.User.class)));
            } else {
            	// NOT FOUND
            	return Optional.empty();
            }
        } catch (IOException e) {
        	return Optional.empty();
        } finally {
        	client.close();
        }
    }
}
