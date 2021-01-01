package operationTree;

public class Operator extends Element {
    Element[] connection;
    Operation operation;
    double value;

    public Operator(Operation operation, Element c1, Element c2, Element c3) {
        this.connection = new Element[]{c1, c2, c3};
        this.operation = operation;
        calculateValue();
    }

    protected Operator() {
        this.connection = new Element[]{null, null, null};
        this.operation = null;
    }

    @Override
    Operator operate(Element section, Operation operation) {
        Operator createdOp = new Operator(operation, this, section, this.connection[2]);
        if (connection[2] != null) connection[2].updateConnection(0, createdOp);
        this.updateConnection(2, createdOp);
        return createdOp;
    }

    @Override
    void updateConnection(int connectionNumber, Operator operator) {
        connection[connectionNumber] = operator;
        if (connectionNumber != 2) {
            calculateValue();
            updateValueTree();
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
        if (this.connection[2] != null) {
            this.connection[2].calculateValue();
            this.connection[2].updateValueTree();
        }
    }

    @Override
    void calculateValue() {
        value = operation.operate(connection[0].getValue(), connection[1].getValue());
    }
}
