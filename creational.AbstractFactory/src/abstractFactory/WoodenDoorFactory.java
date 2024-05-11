package abstractFactory;

import door.Door;
import door.WoodenDoor;
import expert.Carpenter;
import expert.DoorFittingExpert;

public class WoodenDoorFactory implements DoorFactory {
    @Override
    public Door makeDoor() {
        return new WoodenDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Carpenter();
    }
}
