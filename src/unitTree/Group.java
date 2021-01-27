package unitTree;

import java.util.ArrayList;
import java.util.List;

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
        List<Polynomial> innerPolynomials = new ArrayList<>();
        operables.stream().filter(op -> op instanceof Group).forEach(g -> {
            innerPolynomials.add(((Group) g).generatePolynomial());
        });
        return new Polynomial(operables, links, innerPolynomials);
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
