package calculator.model;

import java.util.regex.Pattern;

public class StrCalculator {
    public int add(String input) {
        //빈문자열은 0 반환
        if (isBlank(input)) {
            return 0;
        }
        input = input.replace("\\n", "\n");

        Parsed parsed = parseHeader(input);
        String[] tokens = splitTokens(parsed);

        return sumTokens(tokens);
    }

    // 빈문자열 체크
    private boolean isBlank(String input) {
        return (input == null || input.isBlank());
    }

    //커스텀 구분자와 나머지 숫자 분리(ex: Parsed.deliRegex, Parsed.numbers)
    private Parsed parseHeader(String input) {
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
        return new Parsed(deliRegex, numbers);
    }

    //구분자에 맞춰 숫자 분리
    private String[] splitTokens(Parsed parsed) {
        return parsed.numbers().split(parsed.deliRegex(), -1);
    }

    //숫자 합
    private int sumTokens(String[] tokens) {
        int sum = 0;
        for (String t : tokens) {
            validToken(t);
            int num = Integer.parseInt(t);
            sum += num;
        }
        return sum;
    }

    //각 숫자 예외 처리
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

    //record타입을 사용해서 데아터 저장
    private record Parsed(String deliRegex, String numbers) {
    }
}
