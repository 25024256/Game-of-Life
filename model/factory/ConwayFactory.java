package model.factory;

import model.Cell;
import model.ConwayCell;
import model.strategy.ConwayRules;

public class ConwayFactory implements CellFactory {

    // Gebruikt/hergebruikt de bestaande Conway-regels voor de geboortecheck
    private final ConwayRules rules = new ConwayRules();

    @Override
    public Cell createCell(){
        return new ConwayCell();
    }

    @Override
    public boolean shouldBeBorn(int neighbors){
        // Checkt met regels (niet dubbel)
        return rules.shouldBeBorn(neighbors);
    }
}
