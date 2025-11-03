package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.repository.MemoryPurchaseLottoRepository;
import lotto.util.LottoNumberGeneratorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PurchaseLottoServiceTest {
    private LottoNumberGeneratorImpl lottoNumberGeneratorImpl;
    private MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;
    private PurchaseLottoService purchaseLottoService;
    private int cash;
    private int tryCount;
    private Money money;
    private Lotto lotto;
    private Lotto lotto2;
    private List<Integer> number;
    private List<Lotto> lottos;

    @BeforeEach
    void setUp() {
        memoryPurchaseLottoRepository = new MemoryPurchaseLottoRepository();
        lottoNumberGeneratorImpl = new LottoNumberGeneratorImpl();
        purchaseLottoService = new PurchaseLottoService(memoryPurchaseLottoRepository);
        number = new ArrayList<>();
    }

    @Test
    @DisplayName("로또 구매가 정상적으로 이루어졌는지 목록 확인 (1개 구매)")
    void purchaseOneLottoTest() {
        cash = 1000;
        tryCount = 0;
        money = new Money(cash, tryCount);
        number = setModifiable(List.of(1, 2, 3, 4, 5, 6));
        lottoNumberGeneratorImpl = new FixedLottoNumberGenerator();

        lotto = new Lotto(number);
        lottos = List.of(lotto);
        purchaseLottoService.purchaseLotto(money, lottoNumberGeneratorImpl);

        List<Lotto> compareLotto = memoryPurchaseLottoRepository.findAll();

        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(lottos);
    }

    @Test
    @DisplayName("로또 구매가 정상적으로 이루어졌는지 목록 확인 (2개 구매)")
    void purchaseTwoLottoTest() {
        cash = 2000;
        tryCount = 0;
        money = new Money(cash, tryCount);
        number = setModifiable(List.of(1, 2, 3, 4, 5, 6));
        lottoNumberGeneratorImpl = new FixedLottoNumberGenerator();

        lotto = new Lotto(number);
        lotto2 = new Lotto(number);

        lottos = List.of(lotto, lotto2);
        purchaseLottoService.purchaseLotto(money, lottoNumberGeneratorImpl);

        List<Lotto> compareLotto = memoryPurchaseLottoRepository.findAll();

        assertThat(compareLotto)
                .usingRecursiveComparison()
                .isEqualTo(lottos);
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

    @AfterEach
    void clearRepository() {
        memoryPurchaseLottoRepository.clear();
    }
}


