package lotto.enums;

public enum LottoInputMessage {
    CASH_INPUT_MESSAGE("구입금액을 입력해 주세요."),
    NUMBER_INPUT_MESSAGE("\n당첨 번호를 입력해 주세요."),
    BONUS_NUMBER_INPUT_MESSAGE("\n보너스 번호를 입력해주세요.");

    private final String message;

    private LottoInputMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
