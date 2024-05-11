import simplefactory.Door;
import simplefactory.DoorFactory;

public class Main {
    public static void main(String[] args) {
        Door door = DoorFactory.makeDoor(100, 200);
        System.out.println("Width : " + door.getWidth());
        System.out.println("Height : " + door.getHeight());

        Door door2 = DoorFactory.makeDoor(200, 250);
        System.out.println("Width : " + door2.getWidth());
        System.out.println("Height : " + door2.getHeight());
    }
}