package net.tiny.feature.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

import net.tiny.config.JsonParser;
import net.tiny.ws.BaseWebService;
import net.tiny.ws.HttpHandlerHelper;
import net.tiny.ws.ResponseHeaderHelper;

// API: /v1/demo/users
public class UsersService extends BaseWebService {

	private UsersLoader usersLoader;

	@Override
    protected boolean doGetOnly() {
        return true;
    }

    @Override
    protected void execute(HTTP_METHOD method, HttpExchange he) throws IOException {
    	if (usersLoader == null) {
    		usersLoader = new UsersLoader();
    	}
    	Users users = usersLoader.get();
        if(null != users && !users.isEmpty()) {
        	final ResponseHeaderHelper header = HttpHandlerHelper.getHeaderHelper(he);
        	header.setContentType(MIME_TYPE.JSON);
        	List<Users.User> list = new ArrayList<>();
        	for (Integer id : users.keys()) {
        		list.add(users.find(id));
        	}
        	String body = JsonParser.marshal(list);
        	he.sendResponseHeaders(HttpURLConnection.HTTP_OK, body.length());
            he.getResponseBody().write(body.getBytes());
        } else {
        	he.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, -1);
        }
    }

}
