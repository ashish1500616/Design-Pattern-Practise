public class Main {
    public static void main(String[] args) {

        // Create subscribers - Observers
        JobSeeker johnDoe = new JobSeeker("Ashish");
        JobSeeker janeDoe = new JobSeeker("Aman");

        // Create publisher and attach subscribers - Observable
        EmploymentAgency jobPostings = new EmploymentAgency();
        jobPostings.attach(johnDoe);
        jobPostings.attach(janeDoe);

        // Add a new job and see if subscribers get notified
        jobPostings.addJob(new JobPost("Software Engineer"));

        // Output
        // Hi John Doe! New job posted: Software Engineer
        // Hi Jane Doe! New job posted: Software Engineer
    }
}