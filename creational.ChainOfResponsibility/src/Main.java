import api.PlayVideoAPI;
import models.Request;

public class Main {
    public static void main(String[] args) {
        System.out.println("Implementation of Chain Of Responsibility.");
        new PlayVideoAPI().playVideo(new Request())
    }
}