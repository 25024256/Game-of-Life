package View;

import Model.Clock.TickListener;
import Model.GameBoard;
import Model.Position;
import Model.Cell;
import Model.CellType;
import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GridBoardView extends JPanel implements TickListener {
    private GameBoard board;
    private final int CELL_SIZE = 10; // Grootte van 1 vakje

    // Constructor met parameter
    public GridBoardView(GameBoard board) {
        this.board = board;
        this.setPreferredSize(new Dimension(1000, 1000));
        this.setBackground(Color.WHITE);
    }

    // Elke tick die wordt gegeven → repaint view
    @Override
    public void onTick(long tickNumber) {
        repaint();
    }

    // Tekent
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Zichtbaar grid tekenen
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= 1000; i += CELL_SIZE) {
            g.drawLine(i, 0, i, 1000); // Verticale lijnen
            g.drawLine(0, i, 1000, i); // Horizontale lijnen
        }

        // Cellen tekenen
        for (Map.Entry<Position, Cell> entry : board.getLiveCells().entrySet()) {
            Position pos = entry.getKey();
            Cell cell = entry.getValue();

            // Duidelijk onderscheid tussen celtypen
            if (cell.getType() == CellType.CONWAY) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }

            // Teken de cel
            g.fillRect(pos.getX() * CELL_SIZE, pos.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }
}