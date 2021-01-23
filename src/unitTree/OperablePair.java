package unitTree;

import java.util.ArrayList;
import java.util.List;

public class OperablePair {
    List<Operable> pair;

    public OperablePair(Operable fistOfPair) {
        pair = new ArrayList<>(2);
        pair.add(fistOfPair);
    }

    public OperablePair(Operable fistOfPair, Operable secondOfPair) {
        pair = new ArrayList<>(2);
        pair.add(fistOfPair);
        pair.add(secondOfPair);
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OperablePair) {
            OperablePair cmp = (OperablePair) obj;
            return cmp.pair.equals(this.pair);
        }
        return false;
    }
}
