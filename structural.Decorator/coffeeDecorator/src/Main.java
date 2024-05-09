import addons.MilkCoffee;
import addons.VanillaCoffee;
import addons.WhipCoffee;
import coffeeBase.Coffee;
import coffeeBase.SimpleCoffee;

public class Main {
    public static void main(String[] args) {
        Coffee someCoffee = new SimpleCoffee();
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new MilkCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new WhipCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());

        someCoffee = new VanillaCoffee(someCoffee);
        System.out.println(someCoffee.getCost());
        System.out.println(someCoffee.getDescription());
    }
/*
*
* Output
*
10.0
Simple Coffee
15.0
Milk, Simple Coffee
17.0
Whip,  Milk, Simple Coffee
25.0
Vanilla,  Whip,  Milk, Simple Coffee
* 
* */
}
