package operationTree;

public class Operator /*extends Element */ {

    /*Element[] input;
    Element output;
    Operation operation;
    double value;

    public Operator(Operation operation, Element[] input, Element output) {
        this.input = input;
        this.output = output;
        this.operation = operation;
        calculateValue();
    }

    @Override
    Operator operate(Element[] section, Operation operation) {//REDO
        Operator createdOp = new Operator(operation, this, section, this.output);
        if (output != null) output.updateConnection(0, createdOp);
        section.updateConnection(2, createdOp);
        this.updateConnection(2, createdOp);
        return createdOp;
    }

    @Override
    void updateConnection(int connectionNumber, Operator operator) {//REDO + REFERENCES
        if (connectionNumber != -1) {
            input[connectionNumber] = operator;
            calculateValue();
            updateValueTree();
        } else {
            output = operator;
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
        if (this.output != null) {
            this.output.calculateValue();
            this.output.updateValueTree();
        }
    }

    @Override
    void revert(Element direction) {//REDO
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
        if (output == element) output = null;
    }

    @Override
    void calculateValue() {
        value = operation.operate(Arrays.stream(input).map(Element::getValue).toArray();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Operator)) return false;
        Operator operator = (Operator) obj;
        int i = 0;
        while (i < input.length && operator.input[i] == input[i]) i++;
        return i == input.length && output == operator.output && operator.value == value;
    }*/
}
