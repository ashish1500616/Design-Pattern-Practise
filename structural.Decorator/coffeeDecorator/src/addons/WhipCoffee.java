package addons;

import coffeeBase.Coffee;

// Decorator for adding whip to coffee
public class WhipCoffee implements Coffee {
    private final Coffee coffee;

    public WhipCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 2;
    }

    @Override
    public String getDescription() {
        return " Whip, " + coffee.getDescription();
    }
}
