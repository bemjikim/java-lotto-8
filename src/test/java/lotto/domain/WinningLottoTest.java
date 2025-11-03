package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    private List<Integer> number;

    @BeforeEach
    void setup(){
        number = new ArrayList<>();
    }

    @DisplayName("당첨번호 생성자 테스트 (객체 생성 및 정렬 테스트)")
    @Test
    void winningLottoConstructorTest() {
        List<Integer> numbers = setModifiable(List.of(7, 6, 5, 4, 3, 2, 1));
        List<Integer> compareNumbers = setModifiable(List.of(1, 2, 3, 4, 5, 6, 7));

        WinningLotto winningLotto = new WinningLotto(numbers);

        assertThat(winningLotto)
                .usingRecursiveComparison()
                .isEqualTo(new WinningLotto(compareNumbers));
    }

    // TODO: 번호 비교 및 결과 출력 테스트

    @DisplayName("로또 번호의 개수가 7개가 아닐 경우에 예외를 발생시키는 테스트")
    @Test
    void validateNumberLengthTest() {
        number = setModifiable(List.of(1, 2, 3, 4, 5, 6, 7,  8));

        assertThatThrownBy(() -> new WinningLotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외를 발생시키는 테스트")
    @Test
    void validateDuplicateTest() {
        number = setModifiable(List.of(1, 2, 3, 4, 5, 5, 8));

        assertThatThrownBy(() -> new WinningLotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1~45 범위에 들지 않는 숫자가 있으면 예외를 발생시키는 테스트")
    @Test
    void validateInRangeTest() {
        number = setModifiable(List.of(1, 46, 3, 4, 5, 5, 21));

        assertThatThrownBy(() -> new WinningLotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    List<Integer> setModifiable(List<Integer> numbers) {
        return new ArrayList<>(numbers);
    }
}
