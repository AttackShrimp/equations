package unitTree;

import java.util.ArrayList;
import java.util.List;

public class Group extends Operable {
    List<Operable> operables;
    List<Link> links;
    Polynomial polynomial;

    public Group(String equation) {
        operables = new ArrayList<>();
        links = new ArrayList<>();
        parseEquation(equation);
    }

    public void parseEquation(String equation) {
        StringBuilder stringBuilder = new StringBuilder(equation);
        Link currLink = new Link();
        OperablePair currPair = currLink.parsePair(stringBuilder);
        while (currPair != null && currPair.getSecond() != null) {
            operables.addAll(currPair.getPair());
            links.add(currLink);
            currLink = new Link(currPair.getSecond());
            currPair = currLink.parseNext(stringBuilder);
        }
        if (currPair != null && currPair.getSecond() == null) {
            operables.add(currPair.getFirst());
        }
    }

    public void generatePolynomial() {
        operables.stream().filter(op -> op instanceof Group).forEach(g -> {
            Group group = (Group) g;
            group.generatePolynomial();
            polynomial.add(group.polynomial);
        });
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Group) {
            Group cmp = (Group) obj;
            return cmp.links.equals(this.links);
        }
        return false;
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return (links.size() == 0) ? "[" + operables.get(0).toString() + "]" : links.toString();
    }
}
