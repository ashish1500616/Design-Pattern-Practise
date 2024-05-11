import animals.Dolphin;
import animals.Lion;
import animals.Monkey;
import operations.AnimalOperation;
import operations.Jump;
import operations.Speak;

public class Main {
    public static void main(String[] args) {
        // Creating animals
        Monkey monkey = new Monkey();
        Lion lion = new Lion();
        Dolphin dolphin = new Dolphin();
        // Creating visitor for speaking
        AnimalOperation speak = new Speak();
        // Creating visitor for jumping
        AnimalOperation jump = new Jump();
        // Animal actions
        monkey.accept(speak);  // Ooh oo aa aa!
        monkey.accept(jump);   // Jumped 20 feet high! on to the tree!
        lion.accept(speak);    // Roaaar!
        lion.accept(jump);     // Jumped 7 feet! Back on the ground!
        dolphin.accept(speak); // Tuut tutt tuutt!
        dolphin.accept(jump);  // Walked on water a little and disappeared
    }
}