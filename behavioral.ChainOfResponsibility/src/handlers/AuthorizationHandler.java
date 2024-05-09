package handlers;

import manager.UserManager;
import models.Request;

public class AuthorizationHandler implements RequestHandler {
    private final RequestHandler nextRequestHandler;
    private final UserManager userManager;

    public AuthorizationHandler(RequestHandler nextRequestHandler, UserManager userManager) {
        this.nextRequestHandler = nextRequestHandler;
        this.userManager = userManager;
    }

    @Override
    public void handle(Request request) {
        if (!this.userManager.isSubscribed(request.getRequestHeaders())) {
            throw new RuntimeException("Access Denied !!! . User is not subscribed.");
        }
        System.out.println("Authorization Passed.");
        // so lets say this handler is the tail of the chain.(Linked List.)
        this.nextRequestHandler.handle(request);
    }
}
