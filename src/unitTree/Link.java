package unitTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.NaN;

public class Link {
    private static final int DOUBLE = 0, GROUP = 1, UNKNOWN = 3;
    OperablePair operablePair;
    Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public Link(Operable fistOfPair) {
        operablePair = new OperablePair(fistOfPair);
    }

    public Link() {
        operablePair = new OperablePair();
    }

    public OperablePair parsePair(StringBuilder equation) {
        operablePair = new OperablePair(nextOperable(equation));
        return parseNext(equation);
    }

    public OperablePair parseNext(StringBuilder equation) {
        operation = Operation.parse(equation.charAt(0));
        equation.delete(0, 1);
        operablePair.setSecond(nextOperable(equation));
        return operablePair;
    }

    private Operable nextOperable(StringBuilder equation) {
        char nextChar = equation.charAt(0);
        Operable next;
        if (nextChar == '(') {
            next = new Group(equation.substring(0, equation.indexOf(")")));
        } else if (nextChar >= 'A' && nextChar <= 'z') {
            next = new Unit(getUnknown(equation));
        } else {
            next = new Unit(matchDouble(equation));
        }
        return next;
    }

    private char getUnknown(StringBuilder equation) {
        char id = equation.charAt(0);
        equation.delete(0, 1);
        return id;
    }

    private double matchDouble(StringBuilder equation) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
        Matcher matcher = pattern.matcher(equation);
        if (matcher.find()) {
            double parsedDouble = Double.parseDouble(equation.substring(matcher.start(), matcher.end()));
            equation.delete(matcher.start(), matcher.end());
            return parsedDouble;
        } else {
            return NaN;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Link) {
            Link cmp = (Link) obj;
            return cmp.operablePair.equals(this.operablePair) && cmp.operation.equals(this.operation);
        }
        return false;
    }
}
