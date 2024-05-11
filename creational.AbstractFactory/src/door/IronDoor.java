package door;

public class IronDoor implements Door {

    @Override
    public void getDescription() {
        System.out.println("I am a iron door.");
    }
}
