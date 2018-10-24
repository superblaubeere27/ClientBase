package net.superblaubeere27.clientbase.events;

import com.darkmagician6.eventapi.events.Event;

public class KeyEvent implements Event {
    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
