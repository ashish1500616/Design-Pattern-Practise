public interface Observable {
    void attach(Observer observer);

    void notify(JobPost jobPosting);
}
