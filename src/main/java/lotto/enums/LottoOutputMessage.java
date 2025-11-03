package lotto.enums;

import java.util.List;

import static lotto.view.OutputView.printMatchResult;

public enum LottoOutputMessage {
    NUMBER_OF_BUY_OUTPUT_MESSAGE(-1, "%d개를 구매했습니다.", 0),
    RESULT_STATISTICS_OUTPUT_MESSAGE(-2, "당첨 통계\n---", 0),
    THREE_MATCH_OUTPUT_MESSAGE(3, "3개 일치 (5,000원) - %d개", 5_000),
    FOUR_MATCH_OUTPUT_MESSAGE(4, "4개 일치 (50,000원) - %d개", 50_000),
    FIVE_MATCH_OUTPUT_MESSAGE(5, "5개 일치 (1,500,000원) - %d개", 1_500_000),
    FIVE_AND_BONUS_MATCH_OUTPUT_MESSAGE(51, "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개", 30_000_000),
    SIX_MATCH_OUTPUT_MESSAGE(6, "6개 일치 (2,000,000,000원) - %d개", 2_000_000_000),
    RESULT_OUTPUT_MESSAGE(-3, "총 수익률은 %.1f%%입니다.", 0);

    private final int matchCount;
    private final String message;
    private final long prize;

    private LottoOutputMessage(int matchCount, String message, long prize) {
        this.matchCount = matchCount;
        this.message = message;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public String getMessage() {
        return message;
    }

    public long getPrize() {
        return prize;
    }

    /**
     * 총 몇 개 매칭이 됐는지 확인하는 함수
     *
     * @param compareResult: 각 구매로또와 당첨로또에서 매칭된 숫자를 가진 리스트
     *
     * @return: 각 매칭된 숫자들을 카운트 해서 당첨 번호를 몇 개 맞췄는지 숫자를 반환한다.
     */
    public long countMatches(List<Integer> compareResult) {
        long matchResult = compareResult.stream()
                .filter(n -> n == this.matchCount)
                .count();

        return matchResult;
    }

    /**
     * 당첨 번호와 몇개 일치하는지 확인하고 수익률을 계산하는 함수
     *
     * @param compareResult: 각 구매로또와 당첨로또에서 매칭된 숫자를 가진 리스트
     *
     * @return: 매칭된 숫자 * 금액인 수익을 반환한다
     */
    public long calculateWinningMoney(List<Integer> compareResult) {
        if (this.matchCount <= 0) {
            return 0;
        }

        long matchResult = countMatches(compareResult);
        printMatchResult(String.format(this.message, matchResult));

        return matchResult * prize;
    }
}
