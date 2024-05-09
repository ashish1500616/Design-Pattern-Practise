package adapter;

import lion.Lion;

public class WildDogAdapter implements Lion {
    private WildDog wildDog;

    public WildDogAdapter(WildDog wildDog) {
        this.wildDog = wildDog;
    }

    @Override
    public void roar() {
        wildDog.bark();
    }
}
