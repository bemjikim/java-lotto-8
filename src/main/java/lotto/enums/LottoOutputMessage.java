package lotto.enums;

public enum LottoOutputMessage {
    RESULT_STATISTICS_OUTPUT_MESSAGE("당첨 통계\n" + "---"),
    THREE_MATCH_OUTPUT_MESSAGE("3개 일치 (5,000원) - %d개"),
    FOUR_MATCH_OUTPUT_MESSAGE("4개 일치 (50,000원) - %d개"),
    FIVE_MATCH_OUTPUT_MESSAGE("5개 일치 (1,500,000원) - %d개"),
    FIVE_AND_BONUS_MATCH_OUTPUT_MESSAGE("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개"),
    SIX_MATCH_OUTPUT_MESSAGE("6개 일치 (2,000,000,000원) - %d개"),
    RESULT_OUTPUT_MESSAGE("총 수익률은 %.1f%%입니다.");

    private String message;

    private LottoOutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }

    public String format(Object... args) {
        return String.format(message, args);
    }
}
