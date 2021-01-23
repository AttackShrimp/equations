package unitTree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {
    public static final double[] doubles = new double[]{0, 1, 10, 2334556, 0.0, 10.3, -4, -7.0, -9.009, 0.001};

    @Test
    void parsePair_parses_two_doubles() {
        Arrays.stream(doubles).forEach(d1 -> Arrays.stream(doubles).forEach(d2 -> Arrays.stream(Operation.values()).forEach(op -> {
            Link link = new Link();
            OperablePair expectedResult = new OperablePair(new Unit(d1), new Unit(d2));
            String equation = "" + d1 + op.getSymbol() + d2;
            System.out.println(equation);
            assertEquals(link.parsePair(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (" + d1 + ")(operation)(" + d2 + ")");
            assertEquals(link.getOperation(), Operation.PLUS, equation + " has a " + op + " operation");
        })));
    }

    @Test
    void parseNext() {
    }
}
