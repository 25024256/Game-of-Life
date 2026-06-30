package model;

import model.strategy.ConwayRules;

// Overerven van Cell
public class ConwayCell extends Cell{
    // Lege constructor
    public ConwayCell(){
        // Roept de Cell constructor aan met het Conway-type en de Conway-regels
        super(new ConwayRules(), CellType.CONWAY);
    }
}
