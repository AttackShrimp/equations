package operationTree;

public class BinaryOperator extends Element {
    Element[] input;
    Element output;
    Operation operation;
    double value;

    public BinaryOperator(Operation operation, Element c1, Element c2, Element c3) {
        this.input = new Element[]{c1, c2};
        this.output = c3;
        this.operation = operation;
        calculateValue();
    }

    protected BinaryOperator() {
        this.input = new Element[]{null, null};
        this.output = null;
        this.operation = null;
    }

    @Override
    BinaryOperator operate(Element section, Operation operation) {
        BinaryOperator createdOp = new BinaryOperator(operation, this, section, this.output);
        if (output != null) output.updateConnection(0, createdOp);
        section.updateConnection(2, createdOp);
        this.updateConnection(2, createdOp);
        return createdOp;
    }

    @Override
    void updateConnection(int connectionNumber, BinaryOperator binaryOperator) {
        if (connectionNumber != 2) {//Change
            input[connectionNumber] = binaryOperator;
            calculateValue();
            updateValueTree();
        } else {
            output = binaryOperator;
        }
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public Operation getOperation() {
        return operation;
    }

    @Override
    void updateValueTree() {
        if (output != null) {
            output.calculateValue();
            output.updateValueTree();
        }
    }

    @Override
    void revert(Element direction) {
        Element connection0 = input[0];
        Element connection1 = input[1];
        Element connection2 = output;

        output = direction;

        boolean isLastOperator = connection2 == null;
        if (isLastOperator) {
            connection2 = new Number(value);
        }

        boolean entranceAt0 = connection0.equals(direction);
        connection0 = (entranceAt0) ? connection2 : connection0;
        connection1 = (entranceAt0) ? connection1 : connection2;

        if (operation.commutative() && connection0.getValue() < connection1.getValue()) {
            input[0] = connection1;
            input[1] = connection0;
        } else {
            input[0] = connection0;
            input[1] = connection1;
        }

        if (entranceAt0 || operation.commutative()) operation = operation.revert();

        if (!isLastOperator) {
            connection2.revert(this);
        }

        calculateValue();
    }

    @Override
    void unlink(Element element) {
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(element)) input[i] = null;
        }
        if (output.equals(element)) output = null;
    }

    @Override
    void calculateValue() {
        value = operation.operate(input[0].getValue(), input[1].getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryOperator)) return false;
        BinaryOperator binaryOperator = (BinaryOperator) obj;
        int i = 0;
        while (i < input.length && binaryOperator.input[i] == input[i]) i++;
        return i == input.length && output == binaryOperator.output && binaryOperator.value == value;
    }
}
