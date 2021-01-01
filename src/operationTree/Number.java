package operationTree;

public class Number extends Element {
    public static final Number ONE = new Number(1), ZERO = new Number(0);
    double value;

    public Number(double value) {
        this.value = value;
    }

    public void setValue(double value) {
        //update tree here
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    Operator operate(Number number, Operation operation) {
        double newValue = operation.operate(this.value, number.value);
        Number newNumber = new Number(newValue);
        return new Operator(operation, this, number, newNumber, newNumber.value);
    }

    @Override
    Operator operate(Operator operator, Operation operation) {
        double newValue = operation.operate(this.value, operator.getValue());
        Number newNumber = new Number(newValue);
        return new Operator(operation, this, operator, newNumber, newNumber.value);
    }

    public static double operateOn(double value, Number number, Operation operation) {
        return operation.operate(value, number.value);
    }
}
