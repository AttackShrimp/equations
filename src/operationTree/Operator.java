package operationTree;

public class Operator extends Element {
    Element[] connection;
    Operation operation;
    double value;

    public Operator(Operation operation, Element c1, Element c2, Element c3, double value) {
        this.connection = new Element[]{c1, c2, c3};
        this.operation = operation;
        this.value = value;
    }

    @Override
    Operator operate(Number number, Operation operation) {
        double newValue = Number.operateOn(value, number, operation);
        Number newNumber = new Number(newValue);
        Operator operator = new Operator(operation, this, number, newNumber, newValue);
        this.connection[2] = operator;
        return operator;
    }

    @Override
    Operator operate(Operator operator, Operation operation) {
        double newValue = operation.operate(this.value, operator.getValue());
        Number newNumber = new Number(newValue);
        Operator newOperator = new Operator(operation, this, operator, newNumber, newValue);
        this.connection[2] = newOperator;
        return newOperator;
    }

    @Override
    public double getValue() {
        return value;
    }
}
