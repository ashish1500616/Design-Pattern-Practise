package addons;

import coffeeBase.Coffee;

public class MilkCoffee implements Coffee {
    private final Coffee coffee;

    public MilkCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return " Milk, " + coffee.getDescription();
    }
}
