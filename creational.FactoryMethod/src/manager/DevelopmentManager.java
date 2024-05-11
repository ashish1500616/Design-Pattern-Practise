package manager;

import factory.Developer;
import factory.Interviewer;

public class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new Developer();
    }
}
