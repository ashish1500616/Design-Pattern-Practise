package client;

import implementation.Command;

public class RemoteControl {
    public void submit(Command command) {
        command.execute();
    }
}
