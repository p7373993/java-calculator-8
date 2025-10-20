package calculator.controller;

import calculator.model.StrCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final StrCalculator strCalculator = new StrCalculator();

    public void run() {
        String input = inputView.readLine();
        int result = strCalculator.add(input);
        outputView.printResult(result);
    }

}
