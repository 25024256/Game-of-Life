import Model.Factory.AlternativeFactory;
import Model.Factory.ConwayFactory;
import Model.Factory.CellFactory;
import Model.GameBoard;
import Model.Clock.GameClock;
import Controller.GameController;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Fabrieken maken
        CellFactory conwayFactory = new ConwayFactory();
        CellFactory altFactory = new AlternativeFactory();

        // Fabrieken toevoegen aan Arraylist
        ArrayList<CellFactory> factories = new ArrayList<>();
        factories.add(conwayFactory);
        factories.add(altFactory);

        // Motor aanmaken
        GameBoard board = new GameBoard(factories);
        GameClock clock = new GameClock();

        // Starten
        new GameController(board, clock, conwayFactory, altFactory);
    }
}