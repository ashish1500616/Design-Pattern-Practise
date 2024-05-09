package factory;

import handler.*;

public class EnquiryHandlerFactory {
    private EnquiryHandlerFactory() {
        // Private constructor - As it's a factory.
    }

    public static EnquiryHandler getEnquiryHandler() {
        return new LogHandler(new AcademicEnquiryHandler(new ProjectsEnquiryHandler(new SubscriptionEnquiryHandler(new UnknownEnquiryHandler()))));
    }
}
