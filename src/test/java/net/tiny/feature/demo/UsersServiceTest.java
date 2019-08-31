package net.tiny.feature.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.Test;

import net.tiny.ws.client.SimpleClient;
import net.tiny.ws.rs.client.RestClient;

/**
 * @see https://qiita.com/sahn/items/6088c9dfa35ca2b6fdc2
 */
public class UsersServiceTest {

	@Test
    public void testOnlineService() throws Exception {
        SimpleClient client = new SimpleClient.Builder()
        		.keepAlive(true)
                .build();

        client.doGet(new URL("http://jsonplaceholder.typicode.com/users"), callback -> {
            if(callback.success()) {
                assertEquals(client.getStatus(), HttpURLConnection.HTTP_OK);
                //assertEquals("application/json;charset=utf-8", client.getHeader("Content-Type"));
                System.out.println(client.getHeader("Content-Type"));
                System.out.println(client.getHeader("Transfer-Encoding"));
                final String response = new String(client.getContents());
                System.out.println(response);


            } else {
                Throwable err = callback.cause();
                fail(err.getMessage());
            }
        });
        client.close();
    }

}
