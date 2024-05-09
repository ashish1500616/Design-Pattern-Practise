package handler;

import models.EnquiryType;

public class SubscriptionEnquiryHandler implements EnquiryHandler {
    private final EnquiryHandler nextHandler;

    public SubscriptionEnquiryHandler(EnquiryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public EnquiryType handle(String enquiry) {
        System.out.println("SubscriptionEnquiryHandler.handle");
        if (enquiry == null || enquiry.isEmpty()) {
            throw new IllegalArgumentException("Null Or Empty Enquiry.");
        }
        if ((enquiry.contains("Subscription") || enquiry.contains("Validity") || enquiry.contains("Expiry"))) {
            // Business Logic.
            return EnquiryType.SUBSCRIPTION;
        }
        return this.nextHandler.handle(enquiry);
    }
}
