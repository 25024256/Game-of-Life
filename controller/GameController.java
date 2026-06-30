package controller;

import model.GameBoard;
import model.clock.GameClock;
import model.factory.CellFactory;
import model.stats.StatisticsTracker;
import view.ControlPanel;
import view.GridBoardView;
import view.MainGameWindow;

/** Maakt alle View-onderdelen aan.
  * Koppelt muisklikken, statistieken en listeners aan de klok*/
public class GameController {

    public GameController(GameBoard board, GameClock clock, CellFactory conway, CellFactory alt) {
        // Maakt de View onderdelen
        GridBoardView view = new GridBoardView(board);
        ControlPanel panel = new ControlPanel();

        // Koppelt de muisklikken
        GridInputController input = new GridInputController(board, view, conway, alt);
        view.addMouseListener(input);

        // Koppelt de statistieken
        StatisticsTracker tracker = new StatisticsTracker(board, panel);

        // Voegt abonnees toe aan de klok
        clock.addListener(board);
        clock.addListener(view);
        clock.addListener(tracker);

        // Koppelt Knoppen uit ControlPanel aan GameClock en GameBoard
        panel.startBtn.addActionListener(e -> clock.start());
        panel.pauseBtn.addActionListener(e -> clock.pause());
        panel.resumeBtn.addActionListener(e -> clock.resume());
        panel.fasterBtn.addActionListener(e -> clock.speedUp());
        panel.slowerBtn.addActionListener(e -> clock.slowDown());

        panel.resetBtn.addActionListener(e -> {
            clock.reset(); // Stop klok, ticks naar 0
            board.clearBoard(); // Bord leegmaken
            view.repaint(); // Scherm leegmaken
            panel.updateStats(0, 0, 0); // Tekst op 0 zetten
        });

        // Maakt het venster zichtbaar
        new MainGameWindow(view, panel);
    }
}