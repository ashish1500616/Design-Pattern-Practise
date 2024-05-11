package abstractFactory;

import door.Door;
import door.IronDoor;
import expert.DoorFittingExpert;
import expert.Welder;

public class IronDoorFactory implements DoorFactory {
    @Override
    public Door makeDoor() {
        return new IronDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Welder();
    }
}
