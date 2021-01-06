package operationTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryOperator extends Element {
    List<Element> inputs;
    Element output;
    Operation operation;
    double value;

    public BinaryOperator(Operation operation, Element output, List<Element> inputs) {
        this.inputs = inputs;
        this.output = output;
        this.operation = operation;
        calculateValue();
    }

    public BinaryOperator(Operation operation, Element c1, Element c2, Element c3) {
        this(operation, c3, List.of(c1, c2));
    }

    protected BinaryOperator() {
        this.inputs = new ArrayList<>();
        this.output = null;
        this.operation = null;
    }

    @Override
    BinaryOperator operate(List<Element> sections, Operation operation) {
        List<Element> createdOpInputs = new ArrayList<>(sections);
        createdOpInputs.add(0, this);

        BinaryOperator createdOp = new BinaryOperator(operation, this.output, createdOpInputs);
        if (output != null) output.updateInput(0, createdOp);
        sections.forEach(s -> s.updateOutput(createdOp));
        this.updateOutput(createdOp);
        return createdOp;
    }

    @Override
    void updateInput(int connectionNumber, BinaryOperator binaryOperator) {
        inputs.set(connectionNumber, binaryOperator);
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
    void revert(Element direction) {//REF: calculate options before refactoring
        Element connection0 = inputs.get(0);
        Element connection1 = inputs.get(1);
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
            inputs.set(0, connection1);
            inputs.set(1, connection0);
        } else {
            inputs.set(0, connection0);
            inputs.set(1, connection1);
        }

        if (entranceAt0 || operation.commutative()) operation = operation.revert();

        if (!isLastOperator) {
            connection2.revert(this);
        }

        calculateValue();
    }

    @Override
    void unlink(Element element) {//REF: allow for resizing instead of setting to null
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).equals(element)) inputs.set(i, null);
        }
        if (output.equals(element)) output = null;
    }

    @Override
    void calculateValue() {//REF: allow for list in operation.operate
        value = operation.operate(inputs.get(0).getValue(), inputs.get(1).getValue());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryOperator)) return false;
        BinaryOperator binaryOperator = (BinaryOperator) obj;

        return sameInputs(binaryOperator.inputs, inputs) &&
                output == binaryOperator.output &&
                binaryOperator.value == value;
    }

    private boolean sameInputs(List<Element> a, List<Element> b) {
        int i = 0;
        while (i < a.size() && i < b.size() && a.get(i) == b.get(i)) i++;
        return i == a.size() && i == b.size();
    }
}
