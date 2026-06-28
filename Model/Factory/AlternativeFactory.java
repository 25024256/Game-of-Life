package Model.Factory;

import Model.AlternativeCell;
import Model.Cell;
import Model.Strategy.AlternativeRules;

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
