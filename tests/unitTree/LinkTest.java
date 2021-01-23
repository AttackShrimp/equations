package unitTree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkTest {
    public static final double[] doubles = new double[]{0, 1, 10, 2334556, 0.0, 10.3, -4, -7.0, -9.009, 0.001};
    public static final char[] chars = new char[]{'a', 'A', 'w', 'W', 'Z', 'z'};

    @Test
    void parsePair_parses_two_doubles() {
        Arrays.stream(doubles).forEach(d1 -> Arrays.stream(doubles).forEach(d2 -> Arrays.stream(Operation.values()).forEach(op -> {
            Link link = new Link();
            OperablePair expectedResult = new OperablePair(new Unit(d1), new Unit(d2));
            String equation = "" + d1 + op.getSymbol() + d2;
            assertEquals(link.parsePair(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (" + d1 + ")(operation)(" + d2 + ")");
            assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
        })));
    }

    @Test
    void parsePair_parses_double_and_unknown() {
        Arrays.stream(doubles).forEach(d -> Arrays.stream(Operation.values()).forEach(op -> {
            for (char c : chars) {
                Link link = new Link();
                OperablePair expectedResult = new OperablePair(new Unit(d), new Unit(c));
                String equation = "" + d + op.getSymbol() + c;
                assertEquals(link.parsePair(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (" + d + ")(operation)(" + c + ")");
                assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
            }
        }));
    }

    @Test
    void parsePair_parses_unknown_and_double() {
        Arrays.stream(doubles).forEach(d -> Arrays.stream(Operation.values()).forEach(op -> {
            for (char c : chars) {
                Link link = new Link();
                OperablePair expectedResult = new OperablePair(new Unit(c), new Unit(d));
                String equation = "" + c + op.getSymbol() + d;
                assertEquals(link.parsePair(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (" + c + ")(operation)(" + d + ")");
                assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
            }
        }));
    }

    @Test
    void parsePair_parses_two_unknowns() {
        Arrays.stream(Operation.values()).forEach(op -> {
            for (char c1 : chars) {
                for (char c2 : chars) {
                    Link link = new Link();
                    OperablePair expectedResult = new OperablePair(new Unit(c1), new Unit(c2));
                    String equation = "" + c1 + op.getSymbol() + c2;
                    assertEquals(link.parsePair(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (" + c1 + ")(operation)(" + c2 + ")");
                    assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
                }
            }
        });
    }

    @Test
    void parseNext_parses_double() {
        Unit dummy = new Unit(0);
        Arrays.stream(doubles).forEach(d -> Arrays.stream(Operation.values()).forEach(op -> {
            Link link = new Link();
            OperablePair expectedResult = new OperablePair(dummy, new Unit(d));
            String equation = "" + op.getSymbol() + d;
            assertEquals(link.parseNext(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (operation)(" + d + ")");
            assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
        }));
    }

    @Test
    void parseNext_parses_unknowns() {
        Unit dummy = new Unit(0);
        Arrays.stream(Operation.values()).forEach(op -> {
            for (char c : chars) {
                Link link = new Link();
                OperablePair expectedResult = new OperablePair(dummy, new Unit(c));
                String equation = "" + op.getSymbol() + c;
                assertEquals(link.parseNext(new StringBuilder(equation)), expectedResult, equation + " must be parsed to (operation)(" + c + ")");
                assertEquals(link.getOperation(), op, equation + " has a " + op + " operation");
            }
        });
    }
}
