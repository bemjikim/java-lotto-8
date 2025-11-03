package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.enums.ErrorMessage;
import lotto.enums.LottoInputMessage;

import java.util.NoSuchElementException;

public class InputView {

    public int readWithValidateCashInput() {
        LottoInputMessage.CASH_INPUT_MESSAGE.printMessage();

        String userInput = readInput();
        int cash = validateIsNumberWithBlank(userInput);

        return cash;
    }

    public String readWithValidateNumberInput() {
        LottoInputMessage.NUMBER_INPUT_MESSAGE.printMessage();

        String userInput = readInput();
        validateIsBlank(userInput);

        return userInput;
    }

    public int readWithValidateBonusNumberInput() {
        LottoInputMessage.BONUS_NUMBER_INPUT_MESSAGE.printMessage();

        String userInput = readInput();
        int bonusNumber = validateIsNumberWithBlank(userInput);

        return bonusNumber;
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
            throw new IllegalArgumentException(ErrorMessage.INVALID_BLANK_INPUT.getMessage());
        }
    }

    private int validateIsNumberWithBlank(String userInput) {
        validateIsBlank(userInput);

        try {
            int parseIntUserInput = Integer.parseInt(userInput);

            return parseIntUserInput;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NONE_NUMBER.getMessage());
        }
    }
}


