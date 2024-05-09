import java.util.HashMap;
import java.util.Map;

public class TeaMaker {
    private Map<String, Tea> availableTea = new HashMap<>();

    public Tea make(String preference) {
        if (!availableTea.containsKey(preference)) {
            availableTea.put(preference, new KarakTea());
        }
        return availableTea.get(preference);
    }
}
