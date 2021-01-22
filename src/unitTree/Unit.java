package unitTree;

public class Unit extends Operable {
    int polynomialDegree;
    double constant;
    String id;

    public Unit(double constant) {
        polynomialDegree = 0;
        this.constant = constant;
    }

    public Unit(double constant, String id) {
        this.constant = constant;
        this.id = id;
    }
}
