import operationTree.Number;
import operationTree.Operator;

import static operationTree.Number.number;

public class Main {

    public static void main(String[] args) {
        Number number = number(1);
        Operator oldBase = number(23).plus(7).minus(number.plus(2));
        //System.out.println(oldBase.getValue());
        Operator newBase = (Operator) number.pull();
        System.out.println(newBase.getValue() + ", " + number.getValue());

        //23+7-(x+2)=27
    }

}
