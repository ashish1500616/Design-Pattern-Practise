package animals;

import operations.AnimalOperation;

// Visitee - Interface for animals
public interface Animal {
    void accept(AnimalOperation operations);
}
