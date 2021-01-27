package unitTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group extends Operable {
    List<Operable> operables;
    List<Link> links;

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

    public Polynomial generatePolynomial() {
        Map<Integer, Polynomial> polynomialMap = new HashMap<>();
        operables.stream()
                .filter(op -> op instanceof Group)
                .map(Group.class::cast)
                .forEach(g -> polynomialMap.put(g.hashCode(), g.generatePolynomial()));
        links.forEach(link -> link.replaceOperableIfIn(polynomialMap));

        return new Polynomial(links);
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
