public class Burger {
    private final int size;
    private final boolean cheese;
    private final boolean lettuce;
    private final boolean tomato;

    public Burger(BurgerBuilder burgerBuilder) {
        this.size = burgerBuilder.size;
        this.cheese = burgerBuilder.cheese;
        this.lettuce = burgerBuilder.lettuce;
        this.tomato = burgerBuilder.tomato;
    }

    @Override
    public String toString() {
        return "Burger{" +
                "size=" + size +
                ", cheese=" + cheese +
                ", lettuce=" + lettuce +
                ", tomato=" + tomato +
                '}';
    }
}
