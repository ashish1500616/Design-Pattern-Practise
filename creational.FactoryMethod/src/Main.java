import manager.DevelopmentManager;
import manager.MarketingManager;

public class Main {
    public static void main(String[] args) {
        DevelopmentManager developmentManager = new DevelopmentManager();
        developmentManager.takeInterview();
        MarketingManager marketingManager = new MarketingManager();
        marketingManager.takeInterview();

    }
}