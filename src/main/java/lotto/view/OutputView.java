package lotto.view;

import lotto.enums.LottoOutputMessage;

import java.util.Arrays;
import java.util.List;

import static lotto.enums.LottoOutputMessage.*;

public class OutputView {
    public void printException(IllegalArgumentException e) {
        printMessage(e.getMessage());
    }

    public static void printPurchaseLottoMessage(int tryCount) {
        printMessage(String.format(NUMBER_OF_BUY_OUTPUT_MESSAGE.getMessage(), tryCount));
    }

    public static void printPurchaseLottoNumber(List<Integer> numbers) {
        printMessage(numbers.toString());
    }

    /**
     * 로또 결과를 출력하는 함수
     *
     * @param compareResult: 각 구매로또와 당첨로또에서 매칭된 숫자를 가진 리스트
     * @param totalCash: 사용자가 입력한 금액
     */
    public static void printLottoStatistics(List<Integer> compareResult, int totalCash) {

        printMessage(RESULT_STATISTICS_OUTPUT_MESSAGE.getMessage());

        long totalWinning = Arrays.stream(LottoOutputMessage.values())
                .mapToLong(msg -> msg.calculateWinningMoney(compareResult))
                .sum();

        double profitRate = (double) totalWinning / totalCash * 100;

        printMatchResult(String.format(RESULT_OUTPUT_MESSAGE.getMessage(), profitRate));
    }

    public static void printMatchResult(String matchResult) {
        printMessage(matchResult);
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }
}
