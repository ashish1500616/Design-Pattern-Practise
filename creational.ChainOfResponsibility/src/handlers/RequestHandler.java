package handlers;

import com.sun.net.httpserver.Request;

public interface RequestHandler {

    public void handle(Request request);
}
