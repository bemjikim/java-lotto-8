package lotto.domain;

import lotto.enums.ErrorMessage;

public class Money {
    private final int cash;
    private final int tryCount;

    public Money(int cash, int tryCount) {
        validateIsDivisible(cash);
        this.cash = cash;
        this.tryCount = cash / 1000;
    }

    public int getTryCount() {
        return tryCount;
    }

    public int getCash() {
        return cash;
    }

    private void validateIsDivisible(int cash) {
        if (cash % 1000 == 0) {
            return;
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_NONE_DIVISIBLE.getMessage() + cash);
    }
}
