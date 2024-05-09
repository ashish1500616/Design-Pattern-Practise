import java.util.HashMap;
import java.util.Map;

public class TeaShop {
    private final TeaMaker teaMaker;
    private Map<Integer, Tea> orders = new HashMap<>();

    public TeaShop(TeaMaker teaMaker) {
        this.teaMaker = teaMaker;
    }

    public void takeOrder(String teaType, int table) {
        orders.put(table, teaMaker.make(teaType));
    }

    public void serve() {
        for (Map.Entry<Integer, Tea> entry : orders.entrySet()) {
            System.out.println("Serving tea to table# " + entry.getKey());
        }
    }
}
