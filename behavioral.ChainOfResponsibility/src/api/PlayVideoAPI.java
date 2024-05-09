package api;

import factory.RequestHandlerFactory;
import models.Request;
import models.Response;

public class PlayVideoAPI {
    //    Basic Approach.
    public Response playVideo(Request request) {
        handle(request);
        return null; // For now.
    }

    private void handle(Request request) {
        /*
            // Very simple example of chain of responsibility pattern
            Validation -> Authentication -> Authorization -> IdleHandler.
        */
        RequestHandlerFactory.getHandlers("playVideoAPI").handle(request);
        return;
    }

    /*

        Downsides.
        1. Does not obey - SRP.
        2. Open Closed Principle - Since let's say if you want to introduce third form of handling.
            We will have to make changes in the same class.

        Solutions :
        - Use polymorphism and dependency inversion principle.
                                                -> AuthenticationHandler ( Concrete Class Implementing Handler )
                                              /
            PlayVideoAPI -> RequestHandler  <- ValidationHandler
                            <<Interface>>     \
                                                -> AuthorizationHandler
          But still there is a catch ->
          1 . Order in which these handlers are called in not taken care of.
          2 . Since these concrete handlers are doing there job in isolation there is no way they can communicate with each other.

        In the real world there could be business cases where the handler would want to run the core logic and then forward the
        request to another handler or vice - versa. Therefore each handler would want to know what is the next handler to pass
        down the request or a vice - versa.


     */

}
