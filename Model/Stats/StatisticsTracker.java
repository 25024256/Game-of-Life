package Model.Stats;

import Model.Clock.TickListener;
import Model.GameBoard;

public class StatisticsTracker implements TickListener {
    /** Luistert naar de klok en vraagt het (game)bord om info*/

    private GameStatistics stats;
    private GameBoard board;

    public StatisticsTracker(GameBoard board) {
        this.board = board;
        this.stats = new GameStatistics();
    }

    @Override
    public void onTick(long tickNumber) {
        // Bereken nieuwe statistieken bij elke tik
        int count = board.getLiveCells().size();
        stats.setLivingCellsCount(count);
        stats.setGenerationsCount((int) tickNumber);
    }

    public GameStatistics getStats() {
        return stats;
    }
}
