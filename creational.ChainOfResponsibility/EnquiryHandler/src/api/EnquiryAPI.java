package api;

import factory.EnquiryHandlerFactory;

public class EnquiryAPI {
    public void handleEnquiry(String enquiry) {
        EnquiryHandlerFactory.getEnquiryHandler().handle(enquiry);
    }
}
