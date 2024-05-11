import abstractFactory.DoorFactory;
import abstractFactory.IronDoorFactory;
import abstractFactory.WoodenDoorFactory;
import door.Door;
import expert.DoorFittingExpert;

public class Main {
    public static void main(String[] args) {
        DoorFactory woodenDoorFactory = new WoodenDoorFactory();
        Door woodenDoor = woodenDoorFactory.makeDoor();
        DoorFittingExpert woodenDoorFittingExpert = woodenDoorFactory.makeFittingExpert();

        woodenDoor.getDescription();
        woodenDoorFittingExpert.getDescription();


        DoorFactory ironFactory = new IronDoorFactory();
        Door ironDoor = ironFactory.makeDoor();
        DoorFittingExpert ironDoorFittingExpert = ironFactory.makeFittingExpert();
        ironDoor.getDescription();
        ironDoorFittingExpert.getDescription();
    }
}