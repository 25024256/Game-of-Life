package Model.Strategy;
/** Sterven hoeft niet gecodeerd te worden,
 * want shouldSurvive zorgt er al voor dat alles daarbuiten op false wordt gezet
 * en sterft de cel dus automatisch*/
public class AlternativeRules implements SurvivalStrategy {
    @Override
    // return true als getal groter/gelijk is aan 2 of kleiner/gelijk is aan 4
    public boolean shouldSurvive(int neighbors){
        return neighbors >= 2 && neighbors <= 4;
    }

    @Override
    // return true als getal gelijk is aan 4
    public boolean shouldBeBorn(int neighbors){
        return neighbors == 4;
    }
}
