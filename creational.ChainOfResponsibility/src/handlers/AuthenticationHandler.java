package handlers;

import manager.TokenManager;
import models.Request;

public class AuthenticationHandler implements RequestHandler {

    private final RequestHandler nextRequestHandler;
    private final TokenManager tokenManager;

    public AuthenticationHandler(RequestHandler nextRequestHandler, TokenManager tokenManager) {
        this.nextRequestHandler = nextRequestHandler;
        this.tokenManager = tokenManager;
    }

    @Override
    public void handle(Request request) {
        String email = this.tokenManager.getEmailFromToken(String.valueOf(request.getRequestHeaders()));
        if (!isValidEmail(email)) {
            throw new RuntimeException("Authentication Failed.");
        }
        System.out.println("Authentication Passed.");
        this.nextRequestHandler.handle(request);
    }

    private boolean isValidEmail(String email) {
        return true;
    }
}
