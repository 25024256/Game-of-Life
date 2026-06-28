import Model.Factory.AlternativeFactory;
import Model.Factory.CellFactory;
import Model.Factory.ConwayFactory;
import Model.GameBoard;
import Model.Stats.StatisticsTracker;
import Model.Clock.GameClock;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Maakt een lege lijst aan
        ArrayList<CellFactory> factories = new ArrayList<>();

        /** AANPASSEN: want niet OCP, dus maak het niet meer hardcoded*/
        // Voegt de fabrieken toe
        factories.add(new ConwayFactory());
        factories.add(new AlternativeFactory());

        // Geeft de gevulde lijst aan het bord
        GameBoard board = new GameBoard(factories);

        // Maakt de klok
        GameClock clock = new GameClock();

        // Koppelt de statistieken en het bord aan de klok
        StatisticsTracker tracker = new StatisticsTracker(board);
        clock.addListener(tracker);
        clock.addListener(board);

        // Start
        clock.start();
    }
}