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

            deliRegex = "[,:]|" + quoted;

            numbers = numbers.substring(nl + 1);
        }

        String[] tokens = numbers.split(deliRegex, -1);
        int sum = 0;
        for (String t : tokens) {
            int num = Integer.parseInt(t);
            validToken(t);
            sum += num;
        }
        return sum;
    }

    private void validToken(String token) {

        //연속, 마지막 구분자(ex)"3::2","3:"
        if (token.isEmpty()) {
            throw new IllegalArgumentException("연속, 마지막 구분자 사용 금지");
        }
        //공백 오류
        if (token.contains(" ")) {
            throw new IllegalArgumentException("문자열 내 공백 사용 금지 ");
        }

        //양수가 아닐시 오류
        int num = Integer.parseInt(token);
        if (num <= 0) {
            throw new IllegalArgumentException("양수가 아닙니다");
        }

        //문자열내 구분자,숫자 아닌 문자 포함시 오류
        if (!token.matches("[1-9][0-9]*")) {
            throw new IllegalArgumentException("비허용 문자 사용");
        }
    }
}
