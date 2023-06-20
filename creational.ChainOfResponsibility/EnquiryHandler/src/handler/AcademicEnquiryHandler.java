package handler;

import models.EnquiryType;

public class AcademicEnquiryHandler implements EnquiryHandler {
    private final EnquiryHandler nextHandler;

    public AcademicEnquiryHandler(EnquiryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public EnquiryType handle(String enquiry) {
        System.out.println("AcademicEnquiryHandler.handle");
        if (enquiry == null || enquiry.isEmpty()) {
            throw new IllegalArgumentException("Null Or Empty Enquiry.");
        }
        if ((enquiry.contains("NLP") || enquiry.contains("Data Science") || enquiry.contains("Low Level Design"))) {
            // Business Logic.
            return EnquiryType.ACADEMIC;
        }
        return this.nextHandler.handle(enquiry);
    }
}
