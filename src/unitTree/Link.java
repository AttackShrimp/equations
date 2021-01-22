package unitTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.NaN;

public class Link {
    private static final int DOUBLE = 0, GROUP = 1, UNKNOWN = 3;
    OperablePair operablePair;
    Operation operation;

    public OperablePair parsePair(StringBuilder equation) {

        char nextChar = equation.charAt(0);
        Operable first;
        if (nextChar == '(') {
            first = new Group(equation.substring(0, equation.indexOf(")")));
        } else if (nextChar >= 'A' && nextChar <= 'z') {
            Unit firstOperable = new Unit(matchDouble(equation));
        } else {
            first = new Unit(matchDouble(equation));
        }

        return null;
    }

    private double matchDouble(StringBuilder equation) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
        Matcher matcher = pattern.matcher(equation);
        if (matcher.find()) {
            double parsedDouble = Double.parseDouble(equation.substring(0, matcher.start()));
            equation.delete(0, matcher.start());
            return parsedDouble;
        } else {
            return NaN;
        }
    }
}
