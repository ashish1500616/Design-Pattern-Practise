package manager;

import factory.Interviewer;

abstract class HiringManager {
    private Interviewer interviewer;

    abstract protected Interviewer makeInterviewer();

    public void takeInterview() {
        interviewer = this.makeInterviewer();
        interviewer.askQuestions();
    }
}
