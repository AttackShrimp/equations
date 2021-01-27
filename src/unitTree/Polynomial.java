package unitTree;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    List<PolynomialLevel> levels;

    public Polynomial(List<Operable> operables, List<Link> links, List<Polynomial> innerPolynomials) {
        levels = new ArrayList<>();

        innerPolynomials.forEach(p -> {
            int i = 0;
            while (i < p.levels.size() && i < levels.size()) {
                levels.get(i).add(p.levels.get(i));
                i++;
            }
            while (i < p.levels.size()) {
                levels.add(p.levels.get(i).copy());
                i++;
            }
        });

        operables.stream().filter(op -> op instanceof Unit).forEach(op -> {
            Unit unit = (Unit) op;
            
        });
    }
}
