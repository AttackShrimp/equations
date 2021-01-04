package operationTree;

public abstract class Element {
    public Operator plus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(number, Operation.PLUS);
    }

    public Operator plus(Number number) {
        return operate(number, Operation.PLUS);
    }

    public Operator plus(Operator operator) {
        return operate(operator, Operation.PLUS);
    }

    public Operator minus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(number, Operation.MINUS);
    }

    public Operator minus(Number number) {
        return operate(number, Operation.MINUS);
    }

    public Operator minus(Operator operator) {
        return operate(operator, Operation.MINUS);
    }

    abstract Operator operate(Element section, Operation operation);

    abstract double getValue();

    abstract Operation getOperation();

    abstract void updateConnection(int connectionNumber, Operator operator);

    abstract void calculateValue();

    abstract void updateValueTree();

    abstract void revert(Element direction);

    abstract void unlink(Element element);
}
