package Model.Stats;

public class GameStatistics {
    /**Data Transfer Object → Slaat alle getallen op*/

        private int livingCellsCount;
        private int generationsCount;

        // Setters en Getters om de data bij te houden
        public void setLivingCellsCount(int count) { this.livingCellsCount = count; }
        public void setGenerationsCount(int count) { this.generationsCount = count; }

        public int getLivingCellsCount() { return livingCellsCount; }
        public int getGenerationsCount() { return generationsCount; }
}
