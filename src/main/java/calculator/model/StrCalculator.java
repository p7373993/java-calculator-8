package calculator.model;

import java.util.regex.Pattern;

public class StrCalculator {
    public int add(String input) {
        if (input == null || input.isBlank()) {
            return 0;//공백은 0반환
        }
        input = input.replace("\\n", "\n");

        String deliRegex = "[,:]";
        String numbers = input;

        if (numbers.startsWith("//")) {
            int nl = numbers.indexOf('\n');
            if (nl < 0) {
                throw new IllegalArgumentException("커스텀 구분자 (\\n)이 필요합니다");
            }
            String custom = numbers.substring(2, nl);
            if (custom.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비었습니다");
            }
            String quoted = Pattern.quote(custom);

            deliRegex = "[,:]" + quoted;

            numbers = numbers.substring(nl + 1);
        }

        String[] tokens = numbers.split(deliRegex);
        int sum = 0;
        for (String t : tokens) {
            int num = Integer.parseInt(t);
            if (num <= 0) {
                throw new IllegalArgumentException("양수가 아닙니다" + num);
            }
            sum += num;
        }
        return sum;
    }
}
