public class Main {
    public static void main(String[] args) {

        TeaMaker teaMaker = new TeaMaker();
        TeaShop shop = new TeaShop(teaMaker);

        shop.takeOrder("kadakTea", 1);
        shop.serve();
    }
}