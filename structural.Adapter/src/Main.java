import adapter.WildDog;
import adapter.WildDogAdapter;
import lion.AfricanLion;
import lion.AsianLion;

public class Main {
    public static void main(String[] args) {
        WildDog wildDog = new WildDog();
        WildDogAdapter wildDogAdapter = new WildDogAdapter(wildDog);
        Hunter hunter = new Hunter();
        hunter.hunt(new AsianLion());
        hunter.hunt(new AfricanLion());
        hunter.hunt(wildDogAdapter);
    }
}