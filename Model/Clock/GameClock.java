package Model.Clock;

import java.util.ArrayList;
import java.util.List;

public class GameClock {

    /** Attributen **/
    // Lijst met abbonees
    private List<TickListener> listeners = new ArrayList<>();
    // Teller voor de tijd
    private long tickNumber;
    // Snelheid
    private int speedMs = 1000;
    // Status
    private boolean isRunning = false;

    // Methoden
    public void start() {
        isRunning = true;
        while (isRunning) {
            tickNumber++;
            notifyListeners();
            // Klok even pauzeren
            try {
                Thread.sleep(speedMs);
            } catch (InterruptedException e) {
                System.out.println("Klok is onderbroken");
            }
        }
    }
    public void pause() {
        isRunning = false;
    }
    public void setSpeed(int speedMS){
        this.speedMs = speedMS;
    }

    public List<TickListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<TickListener> listeners) {
        this.listeners = listeners;
    }
    public void addListener(TickListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (TickListener listener : listeners) {
            // Geeft de huidige tijd door aan de abonnee
            listener.onTick(tickNumber);
        }
    }
}
