package Model;

import java.util.*;

public class GameBoard {
    // Map als attribuut meegegeven
    private Map<Position, Cell> liveCells;

    // Initialiseren van de map als Hashmap

    /**
     * Alle levende cellen opslaan.
     * Positie niet op de map?
     * → Vakje automatisch leeg.
     * Bespaart geheugen.
     */
    public GameBoard() {
        // Lege map aanmaken om toe te kunnen voegen
        this.liveCells = new HashMap<>();
    }

    public void addCell(int x, int y, Cell cell) {
        // Maken label (met x en y)
        Position positionLabel = new Position(x, y);
        // Stopt de cel in de map met dat label
        liveCells.put(positionLabel, cell);
    }

    public void removeCell(int x, int y) {
        Position positionLabel = new Position(x, y);
        // Uit de map halen
        liveCells.remove(positionLabel);
    }

    public Cell getCell(int x, int y) {
        Position positionLabel = new Position(x, y);
        // Geef de cel terug die bij dit label hoort (of = 'null')
        return liveCells.get(positionLabel);
    }

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

                // Neighbor op coördinaat (i, j)?
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
                    Position buur = new Position(i, j);
                    if (!liveCells.containsKey(buur)) {
                        emptyNeighbours.add(buur); // Dit is een leeg vakje
                    }
                }
            }
        }

        // Wie wordt er geboren
        for (Position emptyPos : emptyNeighbours) {
            int emptySpotNeighbors = countNeighbours(emptyPos.getX(), emptyPos.getY());

            if (emptySpotNeighbors == 3) {
                nextGeneration.put(emptyPos, new ConwayCell());
            } else if (emptySpotNeighbors == 4) {
                nextGeneration.put(emptyPos, new AlternativeCell());
            }
        }

        // Vervangt het oude bord door het nieuwe
        this.liveCells = nextGeneration;
    }


    public void onTick(long tickNumber) {
        calculateNextGeneration(); // Elke keer als de klok tikt, bereken nieuwe generatie
    }

    // GUI moet weten welke cellen tekenen
    public Map<Position, Cell> getLiveCells() {
        return liveCells;
    }
}
