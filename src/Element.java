public abstract class Element {
    public Operator plus(Number number) {
        return operate(number, Operation.PLUS);
    }

    public Operator plus(Operator operator) {
        return operate(operator, Operation.PLUS);
    }

    public Operator minus(Number number) {
        return operate(number, Operation.MINUS);
    }

    public Operator minus(Operator operator) {
        return operate(operator, Operation.MINUS);
    }

    abstract Operator operate(Number number, Operation operation);

    abstract Operator operate(Operator operator, Operation operation);
}
