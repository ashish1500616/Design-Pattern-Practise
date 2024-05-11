package operations;

import animals.Dolphin;
import animals.Lion;
import animals.Monkey;

// Visitor - Interface for operations on animals
public interface AnimalOperation {
    void visitMonkey(Monkey monkey);

    void visitLion(Lion lion);

    void visitDolphin(Dolphin dolphin);
}
