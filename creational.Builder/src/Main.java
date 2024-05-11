public class Main {
    public static void main(String[] args) {
        Burger burger = new BurgerBuilder(14).addCheese().addLettuce().addTomato().build();
        System.out.println(burger.toString());
        Burger burger2 = new BurgerBuilder(14).addLettuce().addTomato().build();
        System.out.println(burger2.toString());
    }
}