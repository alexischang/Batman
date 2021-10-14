package Internet;

import java.util.ArrayList;

public interface CommandReceiver {
    public void receive(Client.Command command);
}
