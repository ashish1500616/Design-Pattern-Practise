package animals;

import operations.AnimalOperation;

public class Dolphin implements Animal {
    public void speak() {
        System.out.println("Tuut tuttu tuutt!");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitDolphin(this);
    }
}
