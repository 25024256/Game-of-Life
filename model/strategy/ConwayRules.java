package model.strategy;
/** Sterven hoeft niet gecodeerd te worden,
 * want shouldSurvive zorgt er al voor dat alles daarbuiten op false wordt gezet
 * en sterft de cel dus automatisch*/
public class ConwayRules implements SurvivalStrategy {
    @Override
    // return true als getal groter/gelijk is aan 2 of kleiner/gelijk is aan 3
    public boolean shouldSurvive(int neighbors){
        return neighbors >= 2 && neighbors <= 3;
    }

    @Override
    // return true als getal gelijk is aan 3
    public boolean shouldBeBorn(int neighbors){
        return neighbors == 3;
    }
}

