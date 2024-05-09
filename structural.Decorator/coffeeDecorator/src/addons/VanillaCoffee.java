package addons;

import coffeeBase.Coffee;

// Decorator for adding vanilla to coffee
public class VanillaCoffee implements Coffee {
    private final Coffee coffee;

    public VanillaCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public double getCost() {
        return coffee.getCost() + 8;
    }

    public String getDescription() {
        return " Vanilla, " + coffee.getDescription();
    }
}