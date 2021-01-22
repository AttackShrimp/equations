package unitTree;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {
    int maxDegree;
    List<List<Unit>> units;

    public void add(Polynomial polynomial) {
        for (int i = 0; i < maxDegree; i++) {
            units.get(i).addAll(polynomial.units.get(i));
        }
        for (int i = maxDegree; i < polynomial.maxDegree; i++) {
            units.add(new ArrayList<>(polynomial.units.get(i)));
        }
    }
}
