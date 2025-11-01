package lotto.domain;

import java.util.List;

public class Money {
    private final int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        //TODO: 1000원으로 나누어 떨어지는지 검증
    }
}
