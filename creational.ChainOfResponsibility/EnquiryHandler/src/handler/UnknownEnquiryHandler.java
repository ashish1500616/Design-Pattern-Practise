package handler;

import models.EnquiryType;

public class UnknownEnquiryHandler implements EnquiryHandler {
    @Override
    public EnquiryType handle(String enquiry) {
        System.out.println("UnknownEnquiryHandler.handle");
        return EnquiryType.UNKNOWN;
    }
}
