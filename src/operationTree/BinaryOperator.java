package operationTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    void revert(Element direction) {
        int directionIndex = inputs.indexOf(direction);
        inputs.remove(direction);

        boolean isLastOperator = output == null;
        if (isLastOperator) {
            output = new Number(value);
        }

        if (operation.commutative() || directionIndex == 0) {
            operation = operation.revert();
            inputs.add(0, output);
        } else {
            inputs.add(output);
        }

        if (!isLastOperator) {
            output.revert(this);
            output = direction;
        }
        calculateValue();
    }

    @Override
    void unlink(Element element) {
        inputs = inputs.stream().filter(e -> !e.equals(element)).collect(Collectors.toList());
        if (output.equals(element)) output = null;
    }

    @Override
    void calculateValue() {
        value = operation.operate(inputs.stream().mapToDouble(Element::getValue).toArray());
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
        Iterator<Element> aIterator = a.iterator();
        Iterator<Element> bIterator = b.iterator();
        boolean res = a.size() == b.size();
        while (res && aIterator.hasNext()) {
            if (aIterator.next() != bIterator.next()) res = false;
        }
        return res;
    }
}
