package manager;

import factory.CommunityExecutive;
import factory.Interviewer;

public class MarketingManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new CommunityExecutive();
    }
}
