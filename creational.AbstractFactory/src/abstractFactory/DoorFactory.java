package abstractFactory;

import door.Door;
import expert.DoorFittingExpert;

public interface DoorFactory {
    Door makeDoor();

    DoorFittingExpert makeFittingExpert();
}
