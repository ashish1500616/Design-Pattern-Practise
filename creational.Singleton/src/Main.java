public class Main {
    public static void main(String[] args) {
        President president = President.getInstance();
        President president1 = President.getInstance();
        System.out.println(president == president1);

    }
}