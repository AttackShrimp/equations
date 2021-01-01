package operationTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static operationTree.Number.number;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ElementTest {

    static final double[] constant = new double[]{0, 10, -10, 0.001};
    static Number[] nConstant;
    static Operator[] oConstant;

    @BeforeEach
    void setUp() {
        makeNumberConstants();
        makeOperatorConstants();
        makeNumberConstants();
    }

    void makeNumberConstants() {
        nConstant = Arrays.stream(constant).mapToObj(Number::new).toArray(Number[]::new);
    }

    void makeOperatorConstants() {
        oConstant = Arrays.stream(nConstant).map((number(0))::plus).toArray(Operator[]::new);
    }

    private void testOperationWithElement(Element base, Element[] arr2, Operation op) {
        Arrays.stream(arr2).forEach(c -> {
            String msg = base.getValue() + " + " + c + " must be " + (op.operate(base.getValue(), c.getValue()));
            assertEquals((op.operate(base.getValue(), c.getValue())), base.operate(c, op).getValue(), msg);
        });
    }

    @Test
    void number_plus_number() {
        Arrays.stream(nConstant).forEach(n -> {
            makeNumberConstants();
            testOperationWithElement(n, nConstant, Operation.PLUS);
        });
    }

    @Test
    void number_plus_operator() {
        Arrays.stream(nConstant).forEach(n -> {
            makeOperatorConstants();
            testOperationWithElement(n, oConstant, Operation.PLUS);
        });
    }

    @Test
    void operator_plus_operator() {
        Arrays.stream(oConstant).forEach(n -> {
            makeOperatorConstants();
            testOperationWithElement(n, oConstant, Operation.PLUS);
        });
    }

    @Test
    void number_minus_number() {
        Arrays.stream(nConstant).forEach(n -> {
            makeNumberConstants();
            testOperationWithElement(n, nConstant, Operation.MINUS);
        });
    }

    @Test
    void number_minus_operator() {
        Arrays.stream(nConstant).forEach(n -> {
            makeOperatorConstants();
            testOperationWithElement(n, oConstant, Operation.MINUS);
        });
    }

    @Test
    void operator_minus_operator() {
        Arrays.stream(oConstant).forEach(n -> {
            makeOperatorConstants();
            testOperationWithElement(n, oConstant, Operation.MINUS);
        });
    }
}
