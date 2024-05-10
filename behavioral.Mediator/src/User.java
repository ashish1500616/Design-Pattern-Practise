public class User {
    private final String name;
    private final ChatRoomMediator chatRoomMediator;

    public User(String name, ChatRoomMediator chatRoomMediator) {
        this.name = name;
        this.chatRoomMediator = chatRoomMediator;
    }

    public String getName() {
        return name;
    }

    public void send(String message) {
        chatRoomMediator.showMessage(this, message);
    }
}
