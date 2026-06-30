package model.factory;

import model.AlternativeCell;
import model.Cell;
import model.strategy.AlternativeRules;

public class AlternativeFactory implements CellFactory {

    // Gebruikt de bestaande Alternative-regels voor de geboortecheck
    private final AlternativeRules rules = new AlternativeRules();

    @Override
    public Cell createCell(){
        return new AlternativeCell();
    }

    @Override
    public boolean shouldBeBorn(int neighbors){
        // Checkt met regels (niet dubbel)
        return rules.shouldBeBorn(neighbors);
    }
}
