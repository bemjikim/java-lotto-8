package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    private int cash;
    private int tryCount;
    private Money money;

    @DisplayName("입력금액 생성자 테스트 (객체 생성 및 값 테스트)")
    @Test
    void moneyConstructorTest() {
        cash = 1000;
        tryCount = 1;
        money = new Money(cash, 0);

        assertThat(tryCount).isEqualTo(money.getTryCount());
    }

    @DisplayName("입력 금액이 1000원으로 나누어 떨어지지 않으면 예외를 발생시키는 테스트")
    @Test
    void validateIsDivisible() {
        cash = 450;
        tryCount = 0;

        assertThatThrownBy(() -> new Money(450, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
