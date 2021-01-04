package operationTree;

public abstract class Element {
    public BinaryOperator plus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(number, Operation.PLUS);
    }

    public BinaryOperator plus(Number number) {
        return operate(number, Operation.PLUS);
    }

    public BinaryOperator plus(BinaryOperator binaryOperator) {
        return operate(binaryOperator, Operation.PLUS);
    }

    public BinaryOperator minus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(number, Operation.MINUS);
    }

    public BinaryOperator minus(Number number) {
        return operate(number, Operation.MINUS);
    }

    public BinaryOperator minus(BinaryOperator binaryOperator) {
        return operate(binaryOperator, Operation.MINUS);
    }

    abstract BinaryOperator operate(Element section, Operation operation);

    abstract double getValue();

    abstract Operation getOperation();

    abstract void updateConnection(int connectionNumber, BinaryOperator binaryOperator);

    abstract void calculateValue();

    abstract void updateValueTree();

    abstract void revert(Element direction);

    abstract void unlink(Element element);
}
