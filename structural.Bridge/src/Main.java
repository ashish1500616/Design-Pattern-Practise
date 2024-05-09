import theme.DarkTheme;
import theme.LightTheme;
import webpage.AboutPage;
import webpage.LandingPage;

public class Main {
    public static void main(String[] args) {
        DarkTheme darkTheme = new DarkTheme();
        AboutPage aboutPage = new AboutPage(darkTheme);
        LandingPage landingPage = new LandingPage(new LightTheme());
        System.out.println(aboutPage.getContent());
        System.out.println(landingPage.getContent());
    }
}