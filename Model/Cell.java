package Model;

import Model.Strategy.SurvivalStrategy;

public abstract class Cell {
    // Encapsulatie
    private SurvivalStrategy strategy;
    private CellType type;
    private int age;

    //Constructor
    public Cell(SurvivalStrategy strategy, CellType type) {
        this.type = type;
        this.strategy = strategy;
        this.age = 0;
    }

    /**
     * Controleert of deze cel de huidige generatie overleeft op basis van zijn buren.
     * @param neighbors Het aantal levende buren rondom deze cel.
     * @return true als de cel overleeft, false als hij sterft.
     */
    public boolean survives(int neighbors){
        return strategy.shouldSurvive(neighbors);
    }

    /**
     * Verhoogt de leeftijd van de cel met 1.
     * Wordt aangeroepen na elke succesvolle overleving.
     */
    public void incrementAge(){
        age++;
    }

    // Getters
    public int getAge(){
        return age;
    }
    public CellType getType(){
        return type;
    }
}
