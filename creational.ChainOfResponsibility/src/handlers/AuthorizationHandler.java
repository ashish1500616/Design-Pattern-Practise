package handlers;

import com.sun.net.httpserver.Request;
import manager.UserManager;

public class AuthorizationHandler implements RequestHandler {
    private final RequestHandler nextRequestHandler;
    private final UserManager userManager;

    public AuthorizationHandler(RequestHandler nextRequestHandler, UserManager userManager) {
        this.nextRequestHandler = nextRequestHandler;
        this.userManager = userManager;
    }

    @Override
    public void handle(Request request) {
        if (!this.userManager.isSubscribed(request.getRequestHeaders().get("token"))) {
            throw new RuntimeException("Access Denied !!! . User is not subscribed.");
        }
        // so lets say this handler is the tail of the chain.(Linked List.)
    }
}
