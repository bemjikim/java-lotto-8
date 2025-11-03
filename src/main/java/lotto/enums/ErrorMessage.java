package lotto.enums;

public enum ErrorMessage {
    ERROR_PREFIX_MESSAGE("[ERROR] "),
    INVALID_BLANK_INPUT("입력값으로 빈 공백을 허용하지 않습니다"),
    INVALID_NONE_NUMBER("입력값으로 숫자가 아닌 문자를 허용하지 않습니다."),
    INVALID_NONE_DIVISIBLE("입력받은 금액이 1000원으로 나누어 떨어지지 않습니다! [사용자 입력 값]: "),
    INVALID_WINNING_LOTTO_NUMBER_FORMAT("당첨 번호의 형식이 맞지 않습니다! (숫자로 구성, 정확한 구분자 사용) [사용자 입력 값]: "),
    INVALID_DUPLICATE_WINNING_NUMBER("당첨 번호와 보너스 번호를 포함한 숫자들 중 중복되는 숫자가 존재합니다! [사용자 입력 값]: "),
    INVALID_IN_RANGE_WINNING_NUMBER("당첨 번호와 보너스 번호를 포함한 숫자들 중 1~45 범위에 들지 않는 숫자가 존재합니다! [사용자 입력값]: "),
    INVALID_LENGTH_NUMBER("로또 번호는 %d개여야 합니다. [사용자가 입력한 개수]: ");
    private final String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX_MESSAGE.message + message;
    }
}
