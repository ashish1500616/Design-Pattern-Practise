import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatRoom implements ChatRoomMediator {
    @Override
    public void showMessage(User user, String message) {
        String time = new SimpleDateFormat("MMM dd, yy HH:mm").format(new Date());
        String sender = user.getName();

        System.out.println(time + " [" + sender + "]: " + message);
    }
}
