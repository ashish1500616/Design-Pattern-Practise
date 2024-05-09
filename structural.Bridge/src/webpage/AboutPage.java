package webpage;

import theme.Theme;

public class AboutPage implements WebPage {
    private Theme theme;

    public AboutPage(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String getContent() {
        return "About Page in " + theme.getColor();
    }
}
