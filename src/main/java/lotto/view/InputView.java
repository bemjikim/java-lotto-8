package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.enums.LottoInputMessage;

import java.util.NoSuchElementException;

public class InputView {

    public String readWithValidateCashInput() {
        System.out.println(LottoInputMessage.CASH_INPUT_MESSAGE);

        String userInput = readInput();
        validateIsNumberWithBlank(userInput);

        return userInput;
    }

    public String readWithValidateNumberInput() {
        System.out.println(LottoInputMessage.NUMBER_INPUT_MESSAGE);

        String userInput = readInput();
        validateIsBlank(userInput);

        return userInput;
    }

    public String readWithValidateBonusNumberInput() {
        System.out.println(LottoInputMessage.BONUS_NUMBER_INPUT_MESSAGE);

        String userInput = readInput();
        validateIsNumberWithBlank(userInput);

        return userInput;
    }

    private String readInput() {
        String userInput;

        try {
            userInput = Console.readLine();
        } catch (NoSuchElementException e) {
            userInput = "";
        }

        return userInput;
    }

    private void validateIsBlank(String userInput) {
        if (userInput.isBlank()) {
            //TODO: Print Error Message
        }
    }

    private void validateIsNumberWithBlank(String userInput) {
        validateIsBlank(userInput);
        // TODO: 숫자인지 검증
    }
}
