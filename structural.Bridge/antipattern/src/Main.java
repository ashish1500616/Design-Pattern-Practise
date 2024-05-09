interface WebPage {
    String getContent();
}

class AboutDarkTheme implements WebPage {
    public String getContent() {
        return "About page in Dark Black";
    }
}

class AboutLightTheme implements WebPage {
    public String getContent() {
        return "About page in Off white";
    }
}

class CareersDarkTheme implements WebPage {
    public String getContent() {
        return "Careers page in Dark Black";
    }
}

class CareersLightTheme implements WebPage {
    public String getContent() {
        return "Careers page in Off white";
    }
}


public class Main {
    public static void main(String[] args) {
        WebPage aboutDark = new AboutDarkTheme();
        WebPage careersLight = new CareersLightTheme();
        System.out.println(aboutDark.getContent());
        System.out.println(careersLight.getContent());
    }
}