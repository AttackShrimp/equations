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
    void parseEquation_single_group() {
        StringBuilder equation = new StringBuilder();
        StringBuilder linkString = new StringBuilder("[");
        List<Link> links = new ArrayList<>();
        equation.append(var);
        for (int i = 0; i < repeats; i += 2) {
            links.add(new Link(new Unit(var), op, new Unit(num)));
            links.add(new Link(new Unit(num), op, new Unit(var)));
            equation.append(op.getSymbol()).append(num);
            equation.append(op.getSymbol()).append(var);
            linkString.append(var).append(op.getSymbol()).append(num).append(", ")
                    .append(num).append(op.getSymbol()).append(var).append(", ");
        }
        linkString.delete(linkString.length() - 2, linkString.length());
        linkString.append("]");
        Group group = new Group(equation.toString());
        String msg = equation + " should give:\n\t" + linkString + "\nGives:\n\t" + group.getLinks().toString();
        System.out.println(msg);
        assertEquals(group.getLinks(), links, msg);
    }
}
