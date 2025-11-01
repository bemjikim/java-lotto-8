package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

public class LottoNumberGeneratorImpl implements LottoNumberGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;

    @Override
    public List<Integer> generateRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, NUMBER_COUNT);
    }
}
