package handlers;

import models.Request;

public class ValidationHandler implements RequestHandler {
    private final RequestHandler nextRequestHandler;

    public ValidationHandler(RequestHandler requestHandler) {
        this.nextRequestHandler = requestHandler;
    }

    @Override
    public void handle(Request request) {
        // Sanity Checks.
        if (request.getRequestHeaders() == null || request.getRequestHeaders().isEmpty()) {
            throw new IllegalArgumentException("Empty Header");
        }
        // Add Other Sanity Checks.
        System.out.println("Validation Passed.");
        this.nextRequestHandler.handle(request);
    }
}
