import client.RemoteControl;
import implementation.TurnOff;
import implementation.TurnOn;
import receiver.Bulb;

public class Main {
    public static void main(String[] args) {

        Bulb bulb = new Bulb();
        TurnOn turnOn = new TurnOn(bulb);
        TurnOff turnOff = new TurnOff(bulb);

        RemoteControl remote = new RemoteControl();
        remote.submit(turnOn);
        remote.submit(turnOff);
    }
}