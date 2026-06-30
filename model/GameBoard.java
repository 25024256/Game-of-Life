package model;

import model.clock.TickListener;
import model.factory.CellFactory;

import java.util.*;

public class GameBoard implements TickListener {
    // Map als attribuut meegegeven
    /**ENCAPSULATIE: niemand van buitenaf kan een cel in de lijst stoppen
     * vragen via de public methodes*/
    private Map<Position, Cell> liveCells;
    // Lijst van fabrieken
    private List<CellFactory> factories;

    // Stelt de grenzen van grid in
    private final int width = 100;
    private final int height = 100;

    /**
     * Alle levende cellen opslaan.
     * Positie niet op de map?
     * → Vakje automatisch leeg.
     * Bespaart geheugen.
     *
     * @param factories lijst van fabrieken voor elk celtype
     */
    public GameBoard(List<CellFactory> factories) {
        // Lege map aanmaken om toe te kunnen voegen
        this.liveCells = new HashMap<>();
        this.factories = factories;
    }

    // Controleert of een x, y coördinaat binnen ons bord valt
    private boolean isWithinGrid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // Cell toevoegen
    public void addCell(int x, int y, Cell cell) {
        // Alleen toevoegen als de muisklik binnen grid valt
        if (isWithinGrid(x, y)) {
            // Maken label (met x en y)
            Position positionLabel = new Position(x, y);
            // Stopt de cel in de map met dat label
            liveCells.put(positionLabel, cell);
        }
    }

    // Cell verwijderen
    public void removeCell(int x, int y) {
        Position positionLabel = new Position(x, y);
        // Uit de map halen
        liveCells.remove(positionLabel);
    }

    // Buren tellen
    public int countNeighbours(int x, int y) {
        int count = 0;

        // Loop van x-1 tot en met x+1
        for (int i = x - 1; i <= x + 1; i++) {

            // Loop van y-1 tot en met y+1
            for (int j = y - 1; j <= y + 1; j++) {

                // Middelpunt overslaan (de cel geen buur van zichzelf)
                if (i == x && j == y) {
                    continue; // Volgende stap in de loop
                }

                Position neighbourPosition = new Position(i, j);

                /** Als cel in map zit
                 * dan teller omhoog
                 */
                if (liveCells.containsKey(neighbourPosition)) {
                    count++;
                }
            }
        }
        return count;
    }

    // Berekenen nieuw bord (map, overleeft, geboren)
    public void calculateNextGeneration() {
        // Lege map aanmaken voor volgende ronde + lijst voor lege vakjes
        Map<Position, Cell> nextGeneration = new HashMap<>();
        Set<Position> emptyNeighbours = new HashSet<>();

        // Wie overleeft
        for (Map.Entry<Position, Cell> entry : liveCells.entrySet()) {
            Position p = entry.getKey();
            Cell c = entry.getValue();

            int neighbors = countNeighbours(p.getX(), p.getY());

            /** Vraagt aan de cel of hij overleeft (die dit intern doorgeeft aan zijn strategie)
             * Als dat true is:
             * De survives methode wordt aangeroepen
             * verhogen van leeftijd met 1
             * cel toevoegen aan nieuwe generatie
             */
            if (c.survives(neighbors)) {
                c.incrementAge();
                nextGeneration.put(p, c); // Stop hem in de nieuwe map
            }

            // Verzamelen lege vakjes rondom deze levende cel
            for (int i = p.getX() - 1; i <= p.getX() + 1; i++) {
                for (int j = p.getY() - 1; j <= p.getY() + 1; j++) {

                    // Extra commentaar: Kijk alleen naar lege vakjes die binnen ons grid vallen!
                    if (isWithinGrid(i, j)) {
                        Position buur = new Position(i, j);
                        if (!liveCells.containsKey(buur)) {
                            emptyNeighbours.add(buur); // Dit is een leeg vakje
                        }
                    }

                }
            }
        }

        // Wie wordt er geboren
        for (Position emptyPos : emptyNeighbours) {
            int emptySpotNeighbors = countNeighbours(emptyPos.getX(), emptyPos.getY());

            // Vraagt elke fabriek of hij een cel wil aanmaken op dit vakje
            // GameBoard hoeft nooit aangepast te worden bij een nieuw celtype
            for (CellFactory factory : factories) {
                if (factory.shouldBeBorn(emptySpotNeighbors)) {
                    nextGeneration.put(emptyPos, factory.createCell());
                    break; // Eerste match wint, 1 cel per vakje
                }
            }
        }

        // Vervangt het oude bord/generatie door nieuwe
        this.liveCells = nextGeneration;
    }

    // GUI moet weten welke cellen tekenen
    public Map<Position, Cell> getLiveCells() {
        return liveCells;
    }

    // Maakt het bord helemaal leeg
    public void clearBoard() {
        liveCells.clear();
    }

    // Verplichte methode die geïmplementeerd moet worden
    @Override
    public void onTick(long tickNumber) {
        calculateNextGeneration();
    }
}