package Model.Clock;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

public class GameClock {

    /** Attributen **/
    // Lijst met abonnees
    private List<TickListener> listeners = new ArrayList<>();
    // Teller voor de tijd
    private long tickNumber;
    // Snelheid
    private int speedMs = 1000;
    // Status van de klok
    private boolean isRunning = false;

    public void addListener(TickListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        for (TickListener listener : listeners) {
            // Geeft de huidige tijd door aan de abonnee
            listener.onTick(tickNumber);
        }
    }

    /** Methoden **/
    // Start + ticks genereren
    public void start() {
        if (isRunning) {
            // Als de klok al loopt, doe niks
            return;
        }
        isRunning = true;

        // Start thread op achtergrond zodat GUI niet vastloopt
        Thread achtergrondKlok = new Thread() {
            public void run() {
                // Ticks genereren
                while (isRunning) {
                    tickNumber++;
                    notifyListeners();

                    try {
                        Thread.sleep(speedMs);
                    } catch (InterruptedException e) {
                        System.out.println("Klok is onderbroken");
                    }
                }
            }
        };
        achtergrondKlok.start();
    }

    // Pauzeren
    public void pause() {
        // Hierdoor stopt while-loop (hierboven)
        isRunning = false;
    }

    // Hervatten
    public void resume() {
        // Omdat 'tickNumber' onthouden is, hervatten = klok starten
        start();
    }

    // Resetten
    public void reset() {
        pause(); // Stop de klok
        tickNumber = 0; // Terug naar 0
        notifyListeners(); // Laat klassen weten dat op 0 staat
    }

    // Versnellen
    public void speedUp() {
        if (speedMs > 100) { // Zorg dat het niet te snel gaat
            speedMs -= 100;
        }
    }

    // Verlangzamen
    public void slowDown() {
        speedMs += 100;
    }
}
