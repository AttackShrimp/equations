package unitTree;

import java.util.List;

public class Group extends Operable {
    List<Operable> operables;
    List<Link> links;
    Polynomial polynomial;

    public void parseEquation(String equation) {
        StringBuilder stringBuilder = new StringBuilder(equation);
        Link currLink = new Link();
        OperablePair currPair = currLink.parsePair(stringBuilder);
        while (currPair != null) {
            operables.addAll(currPair.getPair());
            links.add(currLink);
            currLink = new Link();
            currPair = currLink.parsePair(equation);
        }
    }

    public void generatePolynomial() {
        operables.stream().filter(op -> op instanceof Group).forEach((Group g) -> {
            g.generatePolynomial();
            polynomial.add(g.polynomial);
        });
    }
}
