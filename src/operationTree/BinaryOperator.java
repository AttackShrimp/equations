package operationTree;

public class BinaryOperator extends Element {
    Element[] inputs;
    Element output;
    Operation operation;
    double value;

    public BinaryOperator(Operation operation, Element output, Element[] inputs) {
        this.inputs = inputs;
        this.output = output;
        this.operation = operation;
        calculateValue();
    }

    public BinaryOperator(Operation operation, Element c1, Element c2, Element c3) {
        this(operation, c3, new Element[]{c1, c2});
    }

    protected BinaryOperator() {
        this.inputs = new Element[]{null, null};
        this.output = null;
        this.operation = null;
    }

    @Override
    BinaryOperator operate(Element section, Operation operation) {
        BinaryOperator createdOp = new BinaryOperator(operation, this, section, this.output);
        if (output != null) output.updateInput(0, createdOp);
        section.updateOutput(createdOp);
        this.updateOutput(createdOp);
        return createdOp;
    }

    @Override
    void updateInput(int connectionNumber, BinaryOperator binaryOperator) {
        inputs[connectionNumber] = binaryOperator;
        calculateValue();
        updateValueTree();
    }

    @Override
    void updateOutput(BinaryOperator binaryOperator) {
        output = binaryOperator;
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
        Element connection0 = inputs[0];
        Element connection1 = inputs[1];
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
            inputs[0] = connection1;
            inputs[1] = connection0;
        } else {
            inputs[0] = connection0;
            inputs[1] = connection1;
        }

        if (entranceAt0 || operation.commutative()) operation = operation.revert();

        if (!isLastOperator) {
            connection2.revert(this);
        }

        calculateValue();
    }

    @Override
    void unlink(Element element) {
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].equals(element)) inputs[i] = null;
        }
        if (output.equals(element)) output = null;
    }

    @Override
    void calculateValue() {
        value = operation.operate(inputs[0].getValue(), inputs[1].getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryOperator)) return false;
        BinaryOperator binaryOperator = (BinaryOperator) obj;
        int i = 0;
        while (i < inputs.length && binaryOperator.inputs[i] == inputs[i]) i++;
        return i == inputs.length && output == binaryOperator.output && binaryOperator.value == value;
    }
}
