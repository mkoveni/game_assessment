package roulette.event;

import java.util.ArrayList;
import java.util.List;

public class RoundChangedEvent {
    private List<RoundChangedListener> listeners;

    public RoundChangedEvent() {
        listeners = new ArrayList<>();
    }

    public void addListener(RoundChangedListener listener) {
        listeners.add(listener);
    }

    public void update(int round) {
        for (RoundChangedListener listener : listeners) {
            listener.update(round);
        }
    }
}
