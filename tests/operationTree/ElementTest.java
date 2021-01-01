package operationTree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static operationTree.Number.number;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementTest {

    static final double[] constant = new double[]{0, 10, -10, 0.001};

    Number[] makeNumbers() {
        return Arrays.stream(constant).mapToObj(Number::new).toArray(Number[]::new);
    }

    Operator[] makeOperators() {
        return Arrays.stream(makeNumbers()).map((number(0))::plus).toArray(Operator[]::new);
    }

    private void testOperationWithElement(Element base, Element[] arr2, Operation op) {
        Arrays.stream(arr2).forEach(c -> {
            String msg = base.getValue() + " + " + c + " must be " + (op.operate(base.getValue(), c.getValue()));
            assertEquals((op.operate(base.getValue(), c.getValue())), base.operate(c, op).getValue(), msg);
        });
    }

    @Test
    void number_plus_number() {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            Number[] numbers2 = makeNumbers();
            testOperationWithElement(n, numbers2, Operation.PLUS);
        });
    }

    @Test
    void number_plus_operator() {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            Operator[] operators = makeOperators();
            testOperationWithElement(n, operators, Operation.PLUS);
        });
    }

    @Test
    void operator_plus_operator() {
        Operator[] operators = makeOperators();
        Arrays.stream(operators).forEach(n -> {
            Operator[] operators2 = makeOperators();
            testOperationWithElement(n, operators2, Operation.PLUS);
        });
    }

    @Test
    void number_minus_number() {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            Number[] numbers2 = makeNumbers();
            testOperationWithElement(n, numbers2, Operation.MINUS);
        });
    }

    @Test
    void number_minus_operator() {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            Operator[] operators = makeOperators();
            testOperationWithElement(n, operators, Operation.MINUS);
        });
    }

    @Test
    void operator_minus_operator() {
        Operator[] operators = makeOperators();
        Arrays.stream(operators).forEach(n -> {
            Operator[] operators2 = makeOperators();
            testOperationWithElement(n, operators2, Operation.MINUS);
        });
    }
}
