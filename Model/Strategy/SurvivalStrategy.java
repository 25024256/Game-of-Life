package Model.Strategy;

public interface SurvivalStrategy {
    boolean shouldSurvive(int neighbors);
    boolean shouldBeBorn(int neighbors);
}
