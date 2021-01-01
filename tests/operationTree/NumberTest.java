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
        assertEquals(0, operation.getValue(), " 7 - ( 1 + 7 - 1) has to equal 0");
    }
}
