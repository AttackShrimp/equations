package operationTree;

public class Number extends BinaryOperator {
    public static final Number ONE = new Number(1), ZERO = new Number(0);

    public Number(double value) {
        this.value = value;
    }

    public static Number number(double value) {
        return new Number(value);
    }

    public void setValue(double value) {
        this.value = value;
        updateValueTree();
    }

    public Element pull() {
        Element res = null;
        if (connection[2] != null) {
            connection[2].revert(this);
            connection[2].unlink(this);
            res = connection[2];
            connection[2] = null;
        }
        return res;
    }
}
