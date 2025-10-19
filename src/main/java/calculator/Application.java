package calculator;

import calculator.model.StrCalculator;
import camp.nextstep.edu.missionutils.Console;


public class Application {
    public static void main(String[] args) {
        String input = Console.readLine();
        int result = new StrCalculator().add(input);
        System.out.println("결과 : " + result);
    }
}
