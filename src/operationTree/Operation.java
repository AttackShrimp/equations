package operationTree;

public enum Operation {
    PLUS, MINUS;

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
}
