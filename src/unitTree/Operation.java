package unitTree;

import java.util.Arrays;

public enum Operation {
    PLUS('+', true),
    MINUS('-', PLUS, false);

    public final char symbol;
    public Operation revertOperation;
    public final boolean commutativeProperty;

    Operation(char symbol, boolean commutativeProperty) {
        this.symbol = symbol;
        this.commutativeProperty = commutativeProperty;
    }

    Operation(char symbol, Operation revertOperation, boolean commutativeProperty) {
        this.symbol = symbol;
        this.revertOperation = revertOperation;
        this.revertOperation.revertOperation = this;
        this.commutativeProperty = commutativeProperty;
    }

    public static Operation parse(char opChar) {
        return Arrays.stream(values()).filter(op -> op.operationOf(opChar)).findFirst().get();
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

    public boolean operationOf(char c) {
        return this.symbol == c;
    }

    public char getSymbol() {
        return symbol;
    }
}
