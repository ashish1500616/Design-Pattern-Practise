public class ComputerFacade {
    private final Computer computer;

    public ComputerFacade(Computer computer) {
        this.computer = computer;
    }

    public void turnOn() {
        System.out.println("Log : Turning On Initiated");
        computer.getElectricShock();
        computer.makeSound();
        computer.showLoadingScreen();
        computer.bam();
        System.out.println("Log : Turning On Complete Successfully\n");
    }

    public void turnOff() {
        System.out.println("Log : Turning Off Initiated");
        computer.closeEverything();
        computer.pullCurrent();
        computer.sooth();
        System.out.println("Log : Turning Off Complete Successfully\n");
    }
}
/*
 *
 * This class provides a simplified interface to the complex Computer class
 *  by encapsulating the computer start-up and shutdown sequences into two methods:
 *  turnOn and turnOff.
 * The facade handles the sequence of operations needed to perform these tasks,
 *  making the client code (like in the main method) cleaner and easier to manage.*/