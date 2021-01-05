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

    BinaryOperator[] makeOperators() {
        return Arrays.stream(makeNumbers()).map((number(0))::plus).toArray(BinaryOperator[]::new);
    }

    private void testOperationWithElement(Element base, Element[] arr2, Operation op) {
        Arrays.stream(arr2).forEach(c -> {
            String msg = base.getValue() + " + " + c + " must be " + (op.operate(base.getValue(), c.getValue()));
            assertEquals((op.operate(base.getValue(), c.getValue())), base.operate(new Element[]{c}, op).getValue(), msg);
        });
    }

    private void number_number(Operation operation) {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            Number[] numbers2 = makeNumbers();
            testOperationWithElement(n, numbers2, operation);
        });
    }

    private void number_operator(Operation operation) {
        Number[] numbers = makeNumbers();
        Arrays.stream(numbers).forEach(n -> {
            BinaryOperator[] binaryOperators = makeOperators();
            testOperationWithElement(n, binaryOperators, operation);
        });
    }

    private void operator_operator(Operation operation) {
        BinaryOperator[] binaryOperators = makeOperators();
        Arrays.stream(binaryOperators).forEach(n -> {
            BinaryOperator[] operators2 = makeOperators();
            testOperationWithElement(n, operators2, operation);
        });
    }

    @Test
    void number_plus_number() {
        number_number(Operation.PLUS);
    }

    @Test
    void number_plus_operator() {
        number_operator(Operation.PLUS);
    }

    @Test
    void operator_plus_operator() {
        operator_operator(Operation.PLUS);
    }

    @Test
    void number_minus_number() {
        number_number(Operation.MINUS);
    }

    @Test
    void number_minus_operator() {
        number_operator(Operation.MINUS);
    }

    @Test
    void operator_minus_operator() {
        operator_operator(Operation.MINUS);
    }

    /*@Test
    void revert_simple_tree() {
        BinaryOperator number = number(1);
        BinaryOperator operation = number(7).minus(number(1).plus(number).minus(1));
        number.setValue(7);
        assertEquals(0, operation.getValue(), " 7 - ( 1 + 7 - 1) has to equal 0");
    }*/
}
