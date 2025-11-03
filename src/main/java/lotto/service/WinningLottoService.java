package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.repository.MemoryWinningLottoRepository;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.WinningLotto.printLottoResult;

public class WinningLottoService {
    private final MemoryWinningLottoRepository memoryWinningLottoRepository;

    public WinningLottoService(MemoryWinningLottoRepository memoryWinningLottoRepository) {
        this.memoryWinningLottoRepository = memoryWinningLottoRepository;
    }

    public void enrollWinningLotto(WinningLotto winningLotto) {
        memoryWinningLottoRepository.save(winningLotto);
    }

    public List<WinningLotto> findWinningLotto() {
        return memoryWinningLottoRepository.findAll();
    }

    /**
     * 당첨 번호와 구입한 로또들을 비교하는 함수
     *
     * @param purchaseLottoService: 구매 로또의 repository에 접근할 수 있는 객체
     *
     * @return: 각 구입로또가 당첨번호와 매칭된 개수 리스트를 반환한다.
     */
    public List<Integer> compareLotto(PurchaseLottoService purchaseLottoService) {
        List<WinningLotto> winningLottoNumber = findWinningLotto();
        List<Lotto> purchaseLottoNumbers = purchaseLottoService.findPurchaseLotto();
        List<Integer> compareResult = new ArrayList<>();

        for (WinningLotto winningLotto : winningLottoNumber) {
            purchaseLottoNumbers.forEach(lotto -> {
                int matchCount = winningLotto.countMatchedNumbers(lotto);
                compareResult.add(matchCount);
            });
        }

        return compareResult;
    }

    public void printWinningLottoResult(Money money, List<Integer> compareResult) {
        printLottoResult(money, compareResult);
    }
}
