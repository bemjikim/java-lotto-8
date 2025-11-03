package lotto.domain;

import lotto.enums.ErrorMessage;

import lotto.util.LottoNumberGeneratorImpl;

import java.util.Collections;
import java.util.List;

import static lotto.view.OutputView.printPurchaseLottoNumber;

public class Lotto {
    private final List<Integer> numbers;
    private static final int NUMBER_LENGTH = 6;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Collections.sort(numbers);
        this.numbers = numbers;
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
     *  랜덤한 로또 번호를 추출하는 함수
     *
     * @param lottoNumberGenerator: 1~45 사이의 중복되지 않는 숫자 6개를 뽑는 객체
     *
     * @return: 숫자 6개를 저장한 lotto 객체 반환
     */
    public static Lotto generateLottoNumbers(LottoNumberGeneratorImpl lottoNumberGenerator) {
        List<Integer> generateNumbers = lottoNumberGenerator.generateLottoNumbers();
        Lotto lotto = new Lotto(generateNumbers);

        return lotto;
    }

    public static void printLottoNumber(Lotto lotto) {
        printPurchaseLottoNumber(lotto.numbers);
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }
}
