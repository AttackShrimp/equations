package operationTree;

public class Number extends Operator {
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
}
