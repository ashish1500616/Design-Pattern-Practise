package handler;

import models.EnquiryType;

public class LogHandler implements EnquiryHandler {
    private final EnquiryHandler nextHandler;

    public LogHandler(EnquiryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public EnquiryType handle(String enquiry) {
        System.out.println("LogHandler.handle");
        // 1st step - Prints the enquiry.        
        System.out.println("enquiry = " + enquiry);
        // 2nd step - Sends enquiry through the chain.        
        EnquiryType enquiryType = this.nextHandler.handle(enquiry);
        System.out.println("enquiryType = " + enquiryType);
        return enquiryType;
    }
}
