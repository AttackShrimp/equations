package operationTree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static operationTree.Number.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    static final double[] constant = new double[]{0, 10, -10, 0.001};
    static Number[] nConstant;
    static Operator[] oConstant;

    @BeforeAll
    static void setUp() {
        nConstant = Arrays.stream(constant).mapToObj(Number::new).toArray(Number[]::new);
        oConstant = Arrays.stream(nConstant).map(ZERO::plus).toArray(Operator[]::new);
    }

    @Test
    void plus_with_double() {
        Arrays.stream(nConstant).forEach(n -> Arrays.stream(constant).forEach(c -> {
            String msg = n.getValue() + " + " + c + " must be " + (n.getValue() + c);
            assertEquals((n.getValue() + c), n.plus(c).getValue(), msg);
        }));
    }

    @Test
    void plus_with_number() {
        testOperationWithNumber(Operation.PLUS);
    }

    private void testOperationWithNumber(Operation op) {
        Arrays.stream(nConstant).forEach(n -> Arrays.stream(nConstant).forEach(c -> {
            String msg = n.getValue() + " + " + c + " must be " + (op.operate(n.getValue(), c.getValue()));
            assertEquals((op.operate(n.getValue(), c.getValue())), n.operate(c, op).getValue(), msg);
        }));
    }

    @Test
    void plus_with_operator() {
        testOperationWithOperator(Operation.PLUS);
    }

    private void testOperationWithOperator(Operation op) {
        Arrays.stream(nConstant).forEach(n -> Arrays.stream(oConstant).forEach(c -> {
            String msg = n.getValue() + " + " + c + " must be " + (op.operate(n.getValue(), c.getValue()));
            assertEquals((op.operate(n.getValue(), c.getValue())), n.operate(c, op).getValue(), msg);
        }));
    }

    @Test
    void minus_with_double() {
        Arrays.stream(nConstant).forEach(n -> Arrays.stream(constant).forEach(c -> {
            String msg = n.getValue() + " + " + c + " must be " + (n.getValue() + c);
            assertEquals((n.getValue() - c), n.minus(c).getValue(), msg);
        }));
    }

    @Test
    void minus_with_number() {
        testOperationWithNumber(Operation.PLUS);
    }

    @Test
    void minus_with_operator() {
        testOperationWithOperator(Operation.MINUS);
    }
}
