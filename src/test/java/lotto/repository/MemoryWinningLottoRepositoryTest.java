package lotto.repository;

import lotto.domain.WinningLotto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryWinningLottoRepositoryTest {
    WinningLotto winningLotto;
    WinningLotto winningLotto2;
    List<WinningLotto> winningLottos;
    MemoryWinningLottoRepository memoryWinningLottoRepository;

    @BeforeEach
    void setup() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7);
        List<Integer> anotherNumbers = List.of(7, 8, 9, 10, 11, 12, 13);
        numbers = setModifiable(numbers);
        anotherNumbers = setModifiable(anotherNumbers);

        winningLotto = new WinningLotto(numbers);
        winningLotto2 = new WinningLotto(anotherNumbers);
        memoryWinningLottoRepository = new MemoryWinningLottoRepository();
    }

    @Test
    @DisplayName("save & findAll이 정상적으로 저장하고 다시 반환하는지 테스트")
    void memoryPurchaseLottoRepositoryTest() {
        winningLottos = List.of(winningLotto);

        memoryWinningLottoRepository.save(winningLotto);
        List<WinningLotto> compareLotto = memoryWinningLottoRepository.findAll();

        // 객체 비교
        assertThat(compareLotto).hasSize(1)
                .containsExactly(winningLotto);

        // 값 비교
        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(winningLottos);

    }

    @Test
    @DisplayName("save & findAll이 정상적으로 저장하고 다시 반환하는지 테스트2")
    void memoryPurchaseLottoRepositoryTest2() {
        winningLottos = List.of(winningLotto, winningLotto2);

        memoryWinningLottoRepository.save(winningLotto);
        memoryWinningLottoRepository.save(winningLotto2);
        List<WinningLotto> compareLotto = memoryWinningLottoRepository.findAll();

        // 객체 비교
        assertThat(compareLotto).hasSize(2)
                .containsExactly(winningLotto, winningLotto2);

        // 값 비교
        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(winningLottos);
    }

    List<Integer> setModifiable(List<Integer> numbers) {
        return new ArrayList<>(numbers);
    }

    @AfterEach
    void clearRepository() {
        memoryWinningLottoRepository.clear();
    }
}
