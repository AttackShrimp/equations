package unitTree;

import java.util.List;

public class Group extends Operable {
    List<Operable> operables;
    List<Link> links;
    Polinomial polinomial;

    public void parseEquation(String equation) {
        Link currLink = new Link();
        OperablePair currPair = currLink.parsePair(equation);
        while (currPair != null) {
            operables.addAll(currPair.getPair());
            links.add(currLink);
            currLink = new Link();
            currPair = currLink.parsePair(equation);
        }
    }

    public void generatePolinomial() {
        operables.stream().filter(op -> op instanceof Group).forEach((Group g) -> {
            g.generatePolinomial();
            polinomial.add(g.polinomial);
        });
    }
}
