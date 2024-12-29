package Observers;

import Server.*;
import States.*;

public interface Subject {
    public void registerObserver(String key, MyObserver o);

    public void removeObserver(String key_to_remove);

    public void notifyObservers();
}
