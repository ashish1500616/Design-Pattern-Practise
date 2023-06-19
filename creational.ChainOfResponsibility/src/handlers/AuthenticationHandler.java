package handlers;

import com.sun.net.httpserver.Request;
import manager.TokenManager;

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
        this.nextRequestHandler.handle(request);
    }

    private boolean isValidEmail(String email) {
        return true;
    }
}
