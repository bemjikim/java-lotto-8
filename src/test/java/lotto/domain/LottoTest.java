package lotto.domain;

import lotto.util.LottoNumberGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    private List<Integer> number;

    @BeforeEach
    void setup(){
        number = new ArrayList<>();
    }

    @DisplayName("로또 생성자 테스트 (객체 생성 및 정렬 테스트)")
    @Test
    void lottoConstructorTest() {
        List<Integer> numbers = setModifiable(List.of(6, 5, 4, 3, 2, 1));
        List<Integer> compareNumbers = setModifiable(List.of(1, 2, 3, 4, 5, 6));

        Lotto lotto = new Lotto(numbers);

        assertThat(lotto)
                .usingRecursiveComparison()
                .isEqualTo(new Lotto(compareNumbers));
    }

    @DisplayName("로또 번호 생성 테스트 (fixed number, not random)")
    @Test
    void generateLottoNumbersTest() {
        List<Integer> numbers = setModifiable(List.of(1, 2, 3, 4, 5, 6));
        LottoNumberGeneratorImpl lottoNumberGenerator = new FixedLottoNumberGenerator();

        Lotto lotto = Lotto.generateLottoNumbers(lottoNumberGenerator);

        assertThat(lotto)
                .usingRecursiveComparison()
                .isEqualTo(new Lotto(numbers));
    }

    @DisplayName("로또 번호의 개수가 6개가 아닐 경우에 예외를 발생시키는 테스트")
    @Test
    void validateNumberLengthTest() {
        number = setModifiable(List.of(1, 2, 3, 4, 5, 6, 7));

        assertThatThrownBy(() -> new Lotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외를 발생시키는 테스트")
    @Test
    void validateDuplicateTest() {
        number = setModifiable(List.of(1, 2, 3, 4, 5, 5));

        assertThatThrownBy(() -> new Lotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 1~45 범위에 들지 않는 숫자가 있으면 예외를 발생시키는 테스트")
    @Test
    void validateInRangeTest() {
        number = setModifiable(List.of(1, 46, 3, 4, 5, 5));

        assertThatThrownBy(() -> new Lotto(number))
                .isInstanceOf(IllegalArgumentException.class);
    }


    List<Integer> setModifiable(List<Integer> numbers) {
        return new ArrayList<>(numbers);
    }

    static class FixedLottoNumberGenerator extends LottoNumberGeneratorImpl {
        @Override
        public List<Integer> generateLottoNumbers() {
            return new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        }
    }
}
