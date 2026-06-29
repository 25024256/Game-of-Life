package Model.Stats;

import Model.Clock.TickListener;
import Model.GameBoard;
import Model.Cell;
import Model.CellType;
import View.ControlPanel;

public class StatisticsTracker implements TickListener {
    private GameBoard board;
    private ControlPanel panel;

    // Constructor met parameters
    public StatisticsTracker(GameBoard board, ControlPanel panel) {
        this.board = board;
        this.panel = panel;
    }

    @Override
    public void onTick(long tickNumber) {
        int conwayCount = 0;
        int altCount = 0;

        // Tel alle cellen per type
        for (Cell cell : board.getLiveCells().values()) {
            if (cell.getType() == CellType.CONWAY) {
                conwayCount++;
            } else {
                altCount++;
            }
        }

        // Stuur ze naar het scherm (ControlPanel)
        panel.updateStats(tickNumber, conwayCount, altCount);
    }
}