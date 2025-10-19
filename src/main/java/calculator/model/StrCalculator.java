package calculator.model;

public class StrCalculator {
    public int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;//공백은 0반환
        }

        String tokens[] = input.split("[,:]");
        int sum = 0;
        for (String t : tokens) {
            sum += Integer.parseInt(t);
        }
        return sum;
    }
}
