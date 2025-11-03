package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.WinningLotto;
import lotto.repository.MemoryPurchaseLottoRepository;
import lotto.repository.MemoryWinningLottoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoServiceTest {
    private PurchaseLottoService purchaseLottoService;
    private MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;
    private WinningLottoService winningLottoService;
    private MemoryWinningLottoRepository memoryWinningLottoRepository;
    private WinningLotto winningLotto;
    List<WinningLotto> winningLottos;
    private Lotto lotto;
    private Lotto lotto2;
    private List<Lotto> lottos;
    private List<Integer> resultLotto;

    @BeforeEach
    void setUp()
    {
        List<Integer> purchaseNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> anotherPurchaseNumbers = List.of(2, 4, 5, 7, 8, 9);
        List<Integer> WinningNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        resultLotto = new ArrayList<>();
        purchaseNumbers = setModifiable(purchaseNumbers);
        anotherPurchaseNumbers = setModifiable(anotherPurchaseNumbers);
        WinningNumbers = setModifiable(WinningNumbers);

        lotto = new Lotto(purchaseNumbers);
        lotto2 = new Lotto(anotherPurchaseNumbers);
        winningLotto = new WinningLotto(WinningNumbers);
        memoryPurchaseLottoRepository = new MemoryPurchaseLottoRepository();
        memoryWinningLottoRepository = new MemoryWinningLottoRepository();
        purchaseLottoService = new PurchaseLottoService(memoryPurchaseLottoRepository);
        winningLottoService = new WinningLottoService(memoryWinningLottoRepository);
    }

    @Test
    @DisplayName("winningLotto와 purchaseLotto 비교 테스트")
    void memoryPurchaseLottoRepositoryTest() {
        lottos = List.of(lotto, lotto2);
        winningLottos = List.of(winningLotto);
        List<Integer> compareResult = List.of(6, 4);

        memoryWinningLottoRepository.save(winningLotto);
        memoryPurchaseLottoRepository.save(lotto);
        memoryPurchaseLottoRepository.save(lotto2);

        resultLotto = winningLottoService.compareLotto(purchaseLottoService);

        assertThat(resultLotto).isEqualTo(compareResult);
    }

    List<Integer> setModifiable(List<Integer> numbers) {
        return new ArrayList<>(numbers);
    }

    @AfterEach
    void clearRepository() {
        memoryPurchaseLottoRepository.clear();
        memoryWinningLottoRepository.clear();
    }
}
