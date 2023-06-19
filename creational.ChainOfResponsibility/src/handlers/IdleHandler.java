package handlers;

import com.sun.net.httpserver.Request;

public class IdleHandler implements RequestHandler {
    @Override
    public void handle(Request request) {
        System.out.print("All Done . Handlers End.");
    }
}
