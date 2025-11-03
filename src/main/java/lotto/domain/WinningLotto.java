package lotto.domain;

import lotto.enums.ErrorMessage;

import java.util.Collections;
import java.util.List;

import static lotto.view.OutputView.printLottoStatistics;

public class WinningLotto {
    private final List<Integer> winningLottoNumber;
    private static final int NUMBER_LENGTH = 7;

    public WinningLotto(List<Integer> winningLottoNumber) {
        validate(winningLottoNumber);
        Collections.sort(winningLottoNumber);
        this.winningLottoNumber = winningLottoNumber;
    }

    private void validate(List<Integer> numbers) {
        validateNumberLength(numbers);
        validateNumberIsDuplicate(numbers);
        validateNumberInRange(numbers);
    }

    private void validateNumberLength(List<Integer> numbers) {
        if (numbers.size() != NUMBER_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LENGTH_NUMBER.getMessage() + NUMBER_LENGTH);
        }
    }

    private void validateNumberIsDuplicate(List<Integer> numbers) {
        if (numbers.size() != numbers.stream().distinct().count()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DUPLICATE_WINNING_NUMBER.getMessage() + numbers);
        }
    }

    private void validateNumberInRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(num -> num < 1 || num > 45)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_IN_RANGE_WINNING_NUMBER.getMessage() + numbers);
        }
    }

    /**
     * 당첨 번호와 구매한 로또와의 비교 함수
     *
     * @param lotto: 구매한 로또의 정보를 가지고 있는 객체
     *
     * @return: 당첨번호와 구매로또의 번호를 비교해서 매칭 숫자를 반환한다.
     */
    public int countMatchedNumbers(Lotto lotto) {
        int matchCount = Math.toIntExact(winningLottoNumber.stream()
                .filter(lotto::contains)
                .count());

        return matchCount;
    }

    public static void printLottoResult(Money money, List<Integer> compareResult) {
        printLottoStatistics(compareResult, money.getCash());
    }
}
