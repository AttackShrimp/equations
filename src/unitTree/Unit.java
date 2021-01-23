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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unit) {
            Unit cmp = (Unit) obj;
            return cmp.constant == this.constant &&
                    cmp.polynomialDegree == this.polynomialDegree &&
                    cmp.id.equals(this.id);
        }
        return false;
    }
}
