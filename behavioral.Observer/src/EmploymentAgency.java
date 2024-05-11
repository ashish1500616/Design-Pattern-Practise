import java.util.ArrayList;
import java.util.List;

public class EmploymentAgency implements Observable {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notify(JobPost jobPosting) {
        for (Observer observer : observers) {
            observer.onJobPosted(jobPosting);
        }
    }

    public void addJob(JobPost jobPosting) {
        notify(jobPosting);
    }
}
