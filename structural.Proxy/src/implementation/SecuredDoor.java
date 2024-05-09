package implementation;

public class SecuredDoor implements Door {
    private final Door door;
    private String password;

    public SecuredDoor(Door door) {
        this.door = door;
    }

    public SecuredDoor setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public void open() {
        if (authenticate(password)) {
            door.open();
        } else {
            System.out.println("Big no! It ain't possible.");
        }
    }

    @Override
    public void close() {
        door.close();
    }

    private boolean authenticate(String password) {
        return password.equals("$ecr@t");
    }

}
