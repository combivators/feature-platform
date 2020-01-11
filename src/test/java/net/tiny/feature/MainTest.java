package net.tiny.feature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.Future;
import java.util.logging.LogManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.tiny.boot.ApplicationContext;
import net.tiny.boot.Main;
import net.tiny.service.ServiceContext;
import net.tiny.feature.demo.Users;
import net.tiny.ws.Launcher;
import net.tiny.ws.rs.client.RestClient;

public class MainTest {

    @BeforeAll
    public static void beforeAll() throws Exception {
        LogManager.getLogManager().readConfiguration(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties"));
    }

    @Test
    public void testFindApplicationConfig() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        loader = getClass().getClassLoader();
        Enumeration<URL> urls = loader.getResources("application-test.properties");
        assertTrue(urls.hasMoreElements());
    }

    @Test
    public void testRestApi() throws Exception {
        String config = "src/test/resources/jaas/jaas.conf";
        System.setProperty("java.security.auth.login.config", config);

        String[] args = new String[] { "-v", "-p", "test" };
        //asynchronous
        ApplicationContext context = new Main(args).run(false);
        assertEquals("test", context.getProfile());

        ServiceContext locator = context.getBean("rest.service", ServiceContext.class);
        assertNotNull(locator);
        Launcher launcher = context.getBootBean(Launcher.class);
        launcher = locator.lookup("launcher", Launcher.class);
        assertNotNull(launcher);
        Thread.sleep(1500L);
        //assertTrue(launcher.isStarting());


        RestClient client = new RestClient.Builder()
                .build();
        RestClient.Response response = client.doGet(new URL("http://localhost:8080/icon/favicon.ico"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        assertTrue(response.getContentLength() > 1);
        response.close();

        response = client.doGet(new URL("http://localhost:8080/v1/api/user/1"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        Users.User user = response.readEntity(Users.User.class);
        assertNotNull(user);
        assertEquals(1, user.id);
        response.close();

        client = new RestClient.Builder()
                .credentials("admin", "password")
                .build();
        response = client.doGet(new URL("http://localhost:8080/v1/demo/users"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        String body = response.getEntity();
        assertNotNull(body);
        response.close();

        client = new RestClient.Builder()
                .credentials("paas", "MyPassword")
                .build();
        response = client.doGet(new URL("http://localhost:8080/sys/status"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        assertEquals("running", response.getEntity());
        response.close();

        response = client.doGet(new URL("http://localhost:8080/sys/stop"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        //launcher.stop();
        Thread.sleep(1000L);

        Future<Integer> result = context.getFuture();
        assertNotNull(result);
        assertEquals(0, result.get().intValue());
    }

}
