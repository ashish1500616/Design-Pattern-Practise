package animals;

import operations.AnimalOperation;

public class Lion implements Animal {
    public void roar() {
        System.out.println("Roaaar!");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitLion(this);
    }
}
