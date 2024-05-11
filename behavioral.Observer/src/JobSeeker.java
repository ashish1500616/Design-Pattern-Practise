public class JobSeeker implements Observer {
    private String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void onJobPosted(JobPost job) {
        System.out.println("Hi " + this.name + "! new job posted: " + job.getTitle());
    }
}
