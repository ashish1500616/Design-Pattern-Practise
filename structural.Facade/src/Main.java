public class Main {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade(new Computer());
        computer.turnOn();  // Outputs: Ouch! Beep beep! Loading.. Ready to be used!
        computer.turnOff(); // Outputs: Bup bup bup buzzzz! Haaah! Zzzzz
    }
}