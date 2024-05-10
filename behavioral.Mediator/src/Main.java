public class Main {
    public static void main(String[] args) {
        ChatRoomMediator mediator = new ChatRoom();

        User john = new User("Ashish", mediator);
        User jane = new User("Aman", mediator);

        john.send("Hi there!");
        jane.send("Hello!");
    }
}