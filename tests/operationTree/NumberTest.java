package operationTree;

import org.junit.jupiter.api.Test;

import static operationTree.Number.number;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    @Test
    void change_value_of_base() {
        Number number = number(1);
        Operator operation = number(7).minus(number(1).plus(number).minus(1));
        number.setValue(7);
        assertEquals(0, operation.getValue(), "7 - ( 1 + 7 - 1) has to equal 0");
    }

    @Test
    void pull_from_left_number() {
        Number number = number(10);
        number(7).minus(number(1).plus(number).minus(1));
        Operator newBase = (Operator) number.pull();
        assertEquals(number.getValue(), newBase.getValue(), "in 7 - ( 1 + x - 1) = -3, x has to equal 10");
    }

    @Test
    void pull_from_right_number() {
        Number number = number(1);
        number(23).plus(7).minus(number.plus(2));
        Operator newBase = (Operator) number.pull();
        assertEquals(number.getValue(), newBase.getValue(), "in 23 + 7 - (x + 2) = 27, x has to equal 1");
    }

    @Test
    void pull_on_small_operator() {
        Number number = number(1);
        number(2).plus(number);
        Operator newBase = (Operator) number.pull();
        assertEquals(number.getValue(), newBase.getValue(), "when changing pull node, the last operator has to update its value");
    }

    @Test
    void reuse_operator_after_number_pull() {
        Number number = number(1);
        number(2).plus(number.plus(7));
        Operator newBase = (Operator) number.pull();
        assertEquals(2, newBase.plus(1).getValue(), "when changing pull node, the last operator has to update its value");
    }
}
