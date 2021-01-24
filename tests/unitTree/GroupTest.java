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

        fillElements(repeats, equation, linkString, links);
        linkString.append("]");

        Group group = new Group(equation.toString());
        String msg = equation + " should give:\n\t" + linkString + "\nGives:\n\t" + group.toString();
        assertEquals(group.getLinks(), links, msg);
    }

    private void fillElements(int elements, StringBuilder equation, StringBuilder linkString, List<Link> links) {
        equation.append(var);
        int i = 1;
        while (i < elements) {
            links.add(new Link(new Unit(var), op, new Unit(num)));
            equation.append(op.getSymbol()).append(num);
            linkString.append(var).append(op.getSymbol()).append(num).append(", ");
            i++;
            if (i < elements) {
                links.add(new Link(new Unit(num), op, new Unit(var)));
                equation.append(op.getSymbol()).append(var);
                linkString.append(num).append(op.getSymbol()).append(var).append(", ");
                i++;
            }
        }
        if (i != 1) linkString.delete(linkString.length() - 2, linkString.length());
        else linkString.append(var);
    }

    private DummyGroup fillGroup(int elements, StringBuilder equation, StringBuilder linkString) {
        List<Link> links = new ArrayList<>();
        linkString.append("[");
        equation.append("(");
        fillElements(elements, equation, linkString, links);
        equation.append(")");
        linkString.append("]");
        return new DummyGroup(links, (elements == 1) ? new Unit(var) : null);
    }

    @Test
    void parseEquation_alternating_groups() {
        StringBuilder equation = new StringBuilder();
        StringBuilder linkString = new StringBuilder("[");
        List<Link> links = new ArrayList<>();

        equation.append(var).append(op.getSymbol());
        linkString.append(var).append(op.getSymbol());
        Group currGroup = fillGroup(1, equation, linkString);
        links.add(new Link(new Unit(var), op, currGroup));
        linkString.append(", ");
        for (int i = 2; i < repeats; i++) {
            equation.append(op.getSymbol());
            linkString.append(currGroup).append(op.getSymbol());
            Group pastGroup = currGroup;
            currGroup = fillGroup(i, equation, linkString);
            links.add(new Link(pastGroup, op, currGroup));
            linkString.append(", ");
        }

        linkString.delete(linkString.length() - 2, linkString.length());
        linkString.append("]");
        Group group = new Group(equation.toString());
        String msg = equation + " should give:\n\t" + linkString + "\nGives:\n\t" + group.toString();

        assertEquals(group.getLinks(), links, msg);
    }

    static class DummyGroup extends Group {
        public DummyGroup(List<Link> links, Unit unit) {
            super("");
            this.links = links;
            if (unit != null) this.operables.add(unit);
        }
    }
}
