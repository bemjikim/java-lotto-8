package lotto.repository;

import lotto.domain.Lotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryPurchaseLottoRepositoryTest {

    private Lotto lotto;
    private Lotto lotto2;
    private List<Lotto> lottos;
    private MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;

    @BeforeEach
    void setup() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = List.of(7, 8, 9, 10, 11, 12);
        numbers = setModifiable(numbers);
        anotherNumbers = setModifiable(anotherNumbers);

        lotto = new Lotto(numbers);
        lotto2 = new Lotto(anotherNumbers);

        memoryPurchaseLottoRepository = new MemoryPurchaseLottoRepository();
    }

    @Test
    @DisplayName("save & findAll이 정상적으로 저장하고 다시 반환하는지 테스트")
    void memoryPurchaseLottoRepositoryTest() {
        lottos = List.of(lotto);

        memoryPurchaseLottoRepository.save(lotto);
        List<Lotto> compareLotto = memoryPurchaseLottoRepository.findAll();

        // 객체 비교
        assertThat(compareLotto).hasSize(1)
                .containsExactly(lotto);

        // 값 비교
        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(lottos);
    }

    @Test
    @DisplayName("save & findAll이 정상적으로 저장하고 다시 반환하는지 테스트2 (객체 2개)")
    void memoryPurchaseLottoRepositoryTest2() {
        lottos = List.of(lotto, lotto2);

        memoryPurchaseLottoRepository.save(lotto);
        memoryPurchaseLottoRepository.save(lotto2);
        List<Lotto> compareLotto = memoryPurchaseLottoRepository.findAll();

        // 객체 비교
        assertThat(compareLotto).hasSize(2)
                .containsExactly(lotto, lotto2);

        // 값 비교
        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(lottos);
    }

    List<Integer> setModifiable(List<Integer> numbers) {
        return new ArrayList<>(numbers);
    }

    @AfterEach
    void clearRepository() {
        memoryPurchaseLottoRepository.clear();
    }
}
