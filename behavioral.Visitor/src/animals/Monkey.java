package animals;

import operations.AnimalOperation;

public class Monkey implements Animal {
    public void shout() {
        System.out.println("Oooh ooo aa aa!.");
    }

    @Override
    public void accept(AnimalOperation operation) {
        operation.visitMonkey(this);
    }
}
