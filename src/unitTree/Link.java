package unitTree;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.NaN;

public class Link {
    private static final int DOUBLE = 0, GROUP = 1, UNKNOWN = 3;
    OperablePair operablePair;
    Operation operation;

    public Link(Operable fistOfPair, Operation operation, Operable secondOfPair) {
        operablePair = new OperablePair(fistOfPair, secondOfPair);
        this.operation = operation;
    }

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
        if (groupEnd(equation)) return null;
        operablePair = new OperablePair(nextOperable(equation));
        return (parseNext(equation) == null && operablePair.getFirst() == null) ? null : operablePair;
    }

    public OperablePair parseNext(StringBuilder equation) {
        if (groupEnd(equation)) return null;
        operation = Operation.parse(equation.charAt(0));
        equation.delete(0, 1);
        if (groupEnd(equation)) return null;
        operablePair.setSecond(nextOperable(equation));
        return operablePair;
    }

    private boolean groupEnd(StringBuilder equation) {
        return equation.length() == 0 || equation.charAt(0) == ')';
    }

    private Operable nextOperable(StringBuilder equation) {
        char nextChar = equation.charAt(0);
        Operable next;
        if (nextChar == '(') {
            int end = closingBracket(equation);
            next = new Group(equation.substring(1, end));
            equation.delete(0, end + 1);
        } else if (nextChar >= 'A' && nextChar <= 'z') {
            next = new Unit(getUnknown(equation));
        } else {
            next = new Unit(matchDouble(equation));
        }
        return next;
    }

    private int closingBracket(StringBuilder equation) {
        int bracketsEntered = 1;
        char[] eq = equation.toString().toCharArray();
        int pos = 1;
        while (pos < eq.length && bracketsEntered > 0) {
            switch (eq[pos]) {
                case '(':
                    bracketsEntered++;
                    break;
                case ')':
                    bracketsEntered--;
                    break;
                default:
                    break;
            }
            pos++;
        }
        return pos == eq.length && eq[pos - 1] != ')' ? -1 : pos - 1;
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

    @Override
    public String toString() {
        return operablePair.getFirst().toString() + operation.getSymbol() + operablePair.getSecond().toString();
    }

    public void replaceOperableIfIn(Map<Integer, Polynomial> polynomialMap) {
        operablePair.replaceWith(polynomialMap);
    }
}
