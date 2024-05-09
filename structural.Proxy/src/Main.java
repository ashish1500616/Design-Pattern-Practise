import implementation.Door;
import implementation.LabDoor;
import implementation.SecuredDoor;

public class Main {
    public static void main(String[] args) {
        Door door = new SecuredDoor(new LabDoor()).setPassword("wrong");
        door.open();
        door = new SecuredDoor(new LabDoor()).setPassword("$ecr@t");
        door.open();
        door.close();


    }
}