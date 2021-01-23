package unitTree;

public class Unit extends Operable {
    public static final Unit EMPTY_UNIT = new Unit(0);

    int polynomialDegree;
    double constant;
    char id;

    public Unit(double constant) {
        polynomialDegree = 0;
        this.constant = constant;
    }

    public Unit(double constant, char id) {
        this.constant = constant;
        this.id = id;
        polynomialDegree = 1;
    }

    public Unit(char id) {
        this(1, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unit) {
            Unit cmp = (Unit) obj;
            return cmp.constant == this.constant &&
                    cmp.polynomialDegree == this.polynomialDegree &&
                    cmp.id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return constant + (id == 0 ? "" : id + "^" + polynomialDegree);
    }
}
