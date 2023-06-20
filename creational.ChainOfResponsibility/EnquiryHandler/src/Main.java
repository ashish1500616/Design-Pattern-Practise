import api.EnquiryAPI;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Handle Your Enquiry!");
        System.out.println("Main.main");
        new EnquiryAPI().handleEnquiry("I want to enroll in NLP Course.");
    }
}