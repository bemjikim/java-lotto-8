package lotto.config;

import lotto.controller.LottoController;
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

    public AppConfig() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.lottoNumberGeneratorImpl = new LottoNumberGeneratorImpl();
    }

    public LottoController settingLottoController() {
        return new LottoController(inputView, outputView, lottoNumberGeneratorImpl);
    }
}
