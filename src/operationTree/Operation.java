package operationTree;

import java.util.Arrays;

public enum Operation {
    PLUS(true),
    MINUS(PLUS, false);

    public Operation revertOperation;
    public final boolean commutativeProperty;

    Operation(boolean commutativeProperty) {
        this.commutativeProperty = commutativeProperty;
    }

    Operation(Operation revertOperation, boolean commutativeProperty) {
        this.revertOperation = revertOperation;
        this.revertOperation.revertOperation = this;
        this.commutativeProperty = commutativeProperty;
    }

    public double operate(double a, double b) {
        double res;
        switch (this) {
            case PLUS:
                res = a + b;
                break;
            case MINUS:
                res = a - b;
                break;
            default:
                res = 0;
                break;
        }
        return res;
    }

    public double operate(double[] a) {
        return Arrays.stream(a).reduce(this::operate).orElse(a[0]);
    }

    public Operation revert() {
        return this.revertOperation;
    }

    public boolean commutative() {
        return this.commutativeProperty;
    }
}
