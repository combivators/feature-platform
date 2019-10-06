package net.tiny.feature.matching;

import static org.junit.jupiter.api.Assertions.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Future;
import java.util.logging.LogManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.tiny.boot.ApplicationContext;
import net.tiny.boot.Main;
import net.tiny.service.ServiceContext;
import net.tiny.feature.model.Entry;
import net.tiny.feature.model.Settings;
import net.tiny.ws.Launcher;
import net.tiny.ws.rs.client.RestClient;

public class MatchingServiceTest {

    @BeforeAll
    public static void beforeAll() throws Exception {
        LogManager.getLogManager().readConfiguration(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties"));
    }

    @Test
    public void testMatching() throws Exception {
        String config = "src/test/resources/jaas/jaas.conf";
        System.setProperty("java.security.auth.login.config", config);

        String[] args = new String[] { "-v", "-p", "test" };
        //asynchronous
        ApplicationContext context = new Main(args).run(false);
        assertEquals("test", context.getProfile());

        ServiceContext serviceContext = context.getBean("service", ServiceContext.class);
        assertNotNull(serviceContext);
        assertNotNull(serviceContext.lookup("launcher", Launcher.class));
        Launcher launcher = context.getBootBean(Launcher.class);

        assertNotNull(launcher);
        Thread.sleep(1000L);
        assertTrue(launcher.isStarting());


        RestClient client = new RestClient.Builder()
                .build();


        RestClient.Response response = client.doGet(new URL("http://localhost:8080/v1/api/match/query/1"));
        assertEquals(response.getStatus(), HttpURLConnection.HTTP_OK);
        Settings settings = response.readEntity(Settings.class);
        assertNotNull(settings);
        //assertEquals(1, user.id);
        response.close();

        response = client.doPost(new URL("http://localhost:8080/v1/api/match/edit/1"), new Entry());
        settings = response.readEntity(Settings.class);
        assertNotNull(settings);
        response.close();

        Thread.sleep(100L);
        launcher.stop();
        Thread.sleep(500L);

        Future<Integer> result = context.getFuture();
        assertNotNull(result);
        assertEquals(0, result.get().intValue());
    }
}
