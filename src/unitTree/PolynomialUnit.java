package unitTree;

public class PolynomialUnit extends Unit {
    int polynomialDegree;

    public PolynomialUnit(double constant, char id, int polynomialDegree) {
        super(constant, id);
        this.polynomialDegree = polynomialDegree;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) &&
                obj instanceof PolynomialUnit &&
                ((PolynomialUnit) obj).polynomialDegree == this.polynomialDegree;
    }

    @Override
    public String toString() {
        String res = "";
        res += (constant == 1) ? "" : constant;
        res += (constant == 0 || polynomialDegree == 0) ? "" : id;
        res += (polynomialDegree == 0 && constant == 1) ? 1 : "";
        res += (constant == 0 || polynomialDegree == 1) ? "" : "^" + polynomialDegree;
        return res;
    }
}
