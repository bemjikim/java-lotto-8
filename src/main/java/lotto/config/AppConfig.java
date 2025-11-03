package lotto.config;

import lotto.controller.LottoController;
import lotto.repository.MemoryPurchaseLottoRepository;
import lotto.repository.MemoryWinningLottoRepository;
import lotto.util.LottoNumberGeneratorImpl;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 의존성 주입
 */
public class AppConfig {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoNumberGeneratorImpl lottoNumberGeneratorImpl;
    private final MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;
    private final MemoryWinningLottoRepository memoryWinningLottoRepository;

    public AppConfig() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoNumberGeneratorImpl = new LottoNumberGeneratorImpl();
        this.memoryPurchaseLottoRepository = new MemoryPurchaseLottoRepository();
        this.memoryWinningLottoRepository = new MemoryWinningLottoRepository();
    }

    /**
     * 의존성 주입 메서드
     * @return: LottoController 의존성 주입
     */
    public LottoController settingLottoController() {
        return new LottoController(inputView, outputView, lottoNumberGeneratorImpl, memoryPurchaseLottoRepository, memoryWinningLottoRepository);
    }
}
