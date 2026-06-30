package model;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Vertelt Java dat twee aparte Position-objecten met
    // dezelfde X en Y als één en dezelfde locatie gezien moeten worden.
    // Dit is onmisbaar als je posities als 'sleutel' in een HashMap gebruikt.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position other = (Position) o;
        return this.x == other.x && this.y == other.y;
    }

    // Werkt samen met equals(). Zorgt ervoor dat posities met
    // dezelfde X en Y ook in hetzelfde 'vakje' van een HashMap belanden.
    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}