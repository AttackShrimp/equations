package unitTree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupTest {

    private static final double num = 0;
    private static final char var = 'a';
    private static final Operation op = Operation.PLUS;

    private static final int repeats = 5;

    @Test
    void parseEquation_sequence_of_numbers() {
        StringBuilder equation = new StringBuilder();
        StringBuilder linkString = new StringBuilder();
        List<Link> links = new ArrayList<>();
        equation.append(num);
        for (int i = 0; i < repeats; i++) {
            links.add(new Link(new Unit(num), op, new Unit(num)));
            equation.append(op.getSymbol()).append(num);
            linkString.append("Link(" + num + op.getSymbol() + num + ")");
        }
        Group group = new Group(equation.toString());
        String msg = equation + " should give:\n\t" + linkString + "\nGives:\n\t" + group.getLinks().toString();
        assertEquals(group.getLinks(), links, msg);
    }
}
