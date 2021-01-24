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
        List<Link> links = new ArrayList<>();

        fillElements(repeats, equation, links);

        Group group = new Group(equation.toString());
        assertEquals(group.getLinks(), links, equation + " should be parsed into equation");
    }

    private void fillElements(int elements, StringBuilder equation, List<Link> links) {
        equation.append(var);
        int i = 1;
        while (i < elements) {
            links.add(new Link(new Unit(var), op, new Unit(num)));
            equation.append(op.getSymbol()).append(num);
            i++;
            if (i < elements) {
                links.add(new Link(new Unit(num), op, new Unit(var)));
                equation.append(op.getSymbol()).append(var);
                i++;
            }
        }
    }

    private DummyGroup fillGroup(int elements, StringBuilder equation) {
        List<Link> links = new ArrayList<>();
        equation.append("(");
        fillElements(elements, equation, links);
        equation.append(")");
        return new DummyGroup(links, (elements == 1) ? new Unit(var) : null);
    }

    @Test
    void parseEquation_alternating_groups() {
        StringBuilder equation = new StringBuilder();
        List<Link> links = new ArrayList<>();

        equation.append(var).append(op.getSymbol());
        Group currGroup = fillGroup(1, equation);
        links.add(new Link(new Unit(var), op, currGroup));
        for (int i = 2; i < repeats; i++) {
            equation.append(op.getSymbol());
            Group pastGroup = currGroup;
            currGroup = fillGroup(i, equation);
            links.add(new Link(pastGroup, op, currGroup));
        }

        Group group = new Group(equation.toString());
        assertEquals(group.getLinks(), links, equation + " should be parsed into equation");
    }

    @Test
    void parseEquation_nested_groups() {
        String equation = "" + num;

        Operable p1 = new Unit(var);
        Operable p2 = new DummyGroup(new ArrayList<>(), new Unit(num));
        ArrayList<Link> linksInGroup = new ArrayList<>();
        for (int i = 0; i < repeats; i++) {
            linksInGroup = new ArrayList<>();
            linksInGroup.add(new Link(p1, op, p2));
            equation = p1.toString() + op.getSymbol() + "(" + equation + ")";
            p2 = new DummyGroup(linksInGroup, null);
            p1 = (i % 2 == 0) ? new Unit(num) : new Unit(var);
        }

        Group group = new Group(equation);
        assertEquals(group.getLinks(), linksInGroup, equation + " should be parsed into equation");
    }

    static class DummyGroup extends Group {
        public DummyGroup(List<Link> links, Unit unit) {
            super("");
            this.links = links;
            if (unit != null) this.operables.add(unit);
        }
    }
}
