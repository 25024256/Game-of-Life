package Model;

import Model.Strategy.AlternativeRules;

// Overerven van Cell
public class AlternativeCell extends Cell{
    // Lege constructor
    public AlternativeCell(){
        // Roept de Cell constructor aan met het Alternative-type en de Alternative-regels
        super(new AlternativeRules(), CellType.ALTERNATIVE);
    }
}
