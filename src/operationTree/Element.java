package operationTree;

import java.util.List;

public abstract class Element {
    public BinaryOperator plus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(List.of(number), Operation.PLUS);
    }

    public BinaryOperator plus(Number number) {
        return operate(List.of(number), Operation.PLUS);
    }

    public BinaryOperator plus(BinaryOperator binaryOperator) {
        return operate(List.of(binaryOperator), Operation.PLUS);
    }

    public BinaryOperator minus(double numberValue) {
        Number number = new Number(numberValue);
        return operate(List.of(number), Operation.MINUS);
    }

    public BinaryOperator minus(Number number) {
        return operate(List.of(number), Operation.MINUS);
    }

    public BinaryOperator minus(BinaryOperator binaryOperator) {
        return operate(List.of(binaryOperator), Operation.MINUS);
    }

    abstract BinaryOperator operate(List<Element> sections, Operation operation);

    abstract double getValue();

    abstract Operation getOperation();

    abstract void updateInput(int connectionNumber, BinaryOperator binaryOperator);

    abstract void updateOutput(BinaryOperator binaryOperator);

    abstract void calculateValue();

    abstract void updateValueTree();

    abstract void revert(Element direction);

    abstract void unlink(Element element);
}
