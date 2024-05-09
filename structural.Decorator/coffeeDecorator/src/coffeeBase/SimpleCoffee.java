package coffeeBase;

// Concrete implementation of SimpleCoffee
public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 10;
    }

    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}
