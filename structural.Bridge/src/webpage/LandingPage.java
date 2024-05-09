package webpage;

import theme.Theme;

public class LandingPage implements WebPage {
    private Theme theme;

    public LandingPage(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "Landing Page in " + theme.getColor();
    }
}
