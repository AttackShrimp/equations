package unitTree;

import java.util.ArrayList;
import java.util.List;

public class OperablePair {
    List<Operable> pair;

    public OperablePair(Operable fistOfPair) {
        pair = new ArrayList<>(2);
        pair.add(fistOfPair);
    }

    public List<Operable> getPair() {
        return pair;
    }

    public Operable getSecond() {
        return pair.get(1);
    }

    public void setSecond(Operable operable) {
        if (pair.size() == 1) {
            pair.add(operable);
        } else {
            pair.set(1, operable);
        }
    }
}
