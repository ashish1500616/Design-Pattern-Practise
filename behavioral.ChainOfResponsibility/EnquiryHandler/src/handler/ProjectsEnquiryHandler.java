package handler;

import models.EnquiryType;

public class ProjectsEnquiryHandler implements EnquiryHandler {
    private final EnquiryHandler nextHandler;

    public ProjectsEnquiryHandler(EnquiryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public EnquiryType handle(String enquiry) {
        System.out.println("ProjectsEnquiryHandler.handle");
        if (enquiry == null || enquiry.isEmpty()) {
            throw new IllegalArgumentException("Null Or Empty Enquiry.");
        }
        if ((enquiry.contains("project") || enquiry.contains("submission") || enquiry.contains("timeline"))) {
            // Business Logic.
            return EnquiryType.PROJECTS;
        }
        return this.nextHandler.handle(enquiry);
    }
}
