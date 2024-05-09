package factory;

import handlers.*;
import manager.TokenManager;
import manager.UserManager;

public class RequestHandlerFactory {
    private RequestHandlerFactory() {
        // Since we don't want a factory to be instantiated.
    }

    public static RequestHandler getHandlers(String apiName) {
        RequestHandler authorizationHandler = new AuthorizationHandler(new IdleHandler(), new UserManager());
        RequestHandler authenticationHandler = new AuthenticationHandler(authorizationHandler, new TokenManager());
        RequestHandler validationHandler = new ValidationHandler(authenticationHandler);
        return validationHandler;
    }
}
