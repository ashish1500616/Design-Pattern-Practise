package simplefactory;

public class DoorFactory {
    public static Door makeDoor(float width, float height) {
        return new WoodenDoor(width, height);
    }
}
