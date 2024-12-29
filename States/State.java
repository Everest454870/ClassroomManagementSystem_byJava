package States;

import Observers.*;
import Server.*;
import java.io.*;

public interface State extends Serializable {

    public void roomReserving(char c, int num);

    public void roomCleaning(char c);

    public void roomOccupying(char c);

    public void endOfOccupation(char c);

    public void userNeedsRepair(char c);

    public void serverNeedsRepair(char c);

    public void completeRepair(char c);

}
