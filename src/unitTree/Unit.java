package unitTree;

public class Unit extends Operable {
    public static final Unit EMPTY_UNIT = new Unit(0);

    double constant;
    char id;

    public Unit(double constant) {
        this.constant = constant;
    }

    public Unit(double constant, char id) {
        this.constant = constant;
        this.id = id;
    }

    public Unit(char id) {
        this(1, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Unit) {
            Unit cmp = (Unit) obj;
            return cmp.constant == this.constant &&
                    cmp.id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        String res = "";
        res += (constant == 1 || constant == 0) ? "" : constant;
        res += (constant == 0) ? "" : id;
        return res;
    }
}
