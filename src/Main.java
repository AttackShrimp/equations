import operationTree.BinaryOperator;
import operationTree.Number;

import static operationTree.Number.number;

public class Main {

    public static void main(String[] args) {
        Number number = number(1);
        BinaryOperator oldBase = number(23).plus(7).minus(number.plus(2));
        //System.out.println(oldBase.getValue());
        BinaryOperator newBase = (BinaryOperator) number.pull();
        System.out.println(newBase.getValue() + ", " + number.getValue());
    }

}
