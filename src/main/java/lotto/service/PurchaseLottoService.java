package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.repository.MemoryPurchaseLottoRepository;
import lotto.util.LottoNumberGeneratorImpl;

import java.util.List;

import static lotto.domain.Lotto.printLottoNumber;
import static lotto.view.OutputView.printPurchaseLottoMessage;

public class PurchaseLottoService {
    private final MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;

    public PurchaseLottoService(MemoryPurchaseLottoRepository memoryPurchaseLottoRepository) {
        this.memoryPurchaseLottoRepository = memoryPurchaseLottoRepository;
    }

    public void purchaseLotto(Money money, LottoNumberGeneratorImpl lottoNumberGenerator) {
        int tryCount = money.getTryCount();

        for (int i = 0; i < tryCount; i++) {
            Lotto lotto = Lotto.generateLottoNumbers(lottoNumberGenerator);
            enrollPurchaseLotto(lotto);
        }
    }

    private void enrollPurchaseLotto(Lotto lotto) {
        memoryPurchaseLottoRepository.save(lotto);
    }

    public void printPurchaseLotto(Money money) {
        List<Lotto> purchaseLotto =  findPurchaseLotto();

        printPurchaseLottoMessage(money.getTryCount());
        for (Lotto lotto : purchaseLotto) {
            printLottoNumber(lotto);
        }
    }

    public List<Lotto> findPurchaseLotto() {
        return memoryPurchaseLottoRepository.findAll();
    }
}
