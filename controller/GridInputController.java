package controller;

import model.GameBoard;
import model.factory.CellFactory;
import view.GridBoardView;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Door "extends MouseAdapter" weet Java nu dat dit een muis-luisteraar is
public class GridInputController extends MouseAdapter {

    // Attributen
    private GameBoard board;
    private GridBoardView view;
    private CellFactory conwayFactory;
    private CellFactory altFactory;

    // Constructor met parameters
    public GridInputController(GameBoard board, GridBoardView view, CellFactory conway, CellFactory alt) {
        this.board = board;
        this.view = view;
        this.conwayFactory = conway;
        this.altFactory = alt;
    }

    // Methode wordt automatisch aangeroepen als je klikt
    @Override
    public void mouseClicked(MouseEvent e) {
        // Delen door 10, omdat onze vakjes 10 pixels breed/hoog zijn in GridBoardView
        int x = e.getX() / 10;
        int y = e.getY() / 10;

        // Verwijderen (Ctrl + Klik)
        if (e.isControlDown()) {
            board.removeCell(x, y);
        }
        // Conway cel plaatsen (Linkermuisknop)
        else if (SwingUtilities.isLeftMouseButton(e)) {
            board.addCell(x, y, conwayFactory.createCell());
        }
        // Alternatieve cel plaatsen (Rechtermuisknop)
        else if (SwingUtilities.isRightMouseButton(e)) {
            board.addCell(x, y, altFactory.createCell());
        }

        // Vertelt scherm dat er iets veranderd is → nieuwe blokje tekent
        view.repaint();
    }
}