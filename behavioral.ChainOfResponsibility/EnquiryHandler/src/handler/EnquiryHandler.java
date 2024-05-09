package handler;

import models.EnquiryType;

public interface EnquiryHandler {
    EnquiryType handle(String enquiry);
}
