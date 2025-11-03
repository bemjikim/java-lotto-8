package lotto.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumberGeneratorTest {
    private LottoNumberGeneratorImpl lottoNumberGeneratorImpl;

    @BeforeEach
    void setUp() {
        lottoNumberGeneratorImpl = new LottoNumberGeneratorImpl();
    }

    @Test
    @DisplayName("1에서 45사이 랜덤한 값 생성 테스트")
    void generateRandomLottoNumberTest() {
        List<Integer> lottoNumber = lottoNumberGeneratorImpl.generateLottoNumbers();

        assertThat(lottoNumber)
                .allMatch(num -> num >= 1 && num <= 45);
    }

    @Test
    @DisplayName("1에서 45사이 랜덤한 값 생성 및 중복 테스트")
    void generateRandomLottoNumberDuplicateTest() {
        List<Integer> lottoNumber = lottoNumberGeneratorImpl.generateLottoNumbers();

        assertThat(new HashSet<>(lottoNumber).size())
                .isEqualTo(lottoNumber.size());
    }

    @Test
    @DisplayName("반복테스트(10000회): 1에서 45사이 랜덤한 값 생성 및 중복 테스트")
    void multipleGenerateRandomLottoNumberDuplicateTest() {
        IntStream.range(0, 10_000).forEach(i -> {
            List<Integer> lottoNumbers = lottoNumberGeneratorImpl.generateLottoNumbers();

            assertThat(lottoNumbers)
                    .allMatch(num -> num >= 1 && num <= 45);

            assertThat(new HashSet<>(lottoNumbers).size())
                    .isEqualTo(lottoNumbers.size());
        });
    }
}
