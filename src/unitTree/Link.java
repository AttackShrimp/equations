package unitTree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Link {
    OperablePair operablePair;
    Operation operation;

    public OperablePair parsePair(StringBuilder equation) {
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
        Matcher matcher = pattern.matcher(equation);
        matcher.find();
        Operable firstOperable = new Unit(Double.parseDouble(equation.substring(0, matcher.start())));

        equation.delete(0, matcher.start());

        return null;
    }
}
