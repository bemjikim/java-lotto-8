package lotto.controller;

import lotto.util.LottoNumberGeneratorImpl;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private InputView inputView;
    private OutputView outputView;
    private LottoNumberGeneratorImpl lottoNumberGeneratorImpl;

    public LottoController(InputView inputView, OutputView outputView, LottoNumberGeneratorImpl lottoNumberGeneratorImpl) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoNumberGeneratorImpl = lottoNumberGeneratorImpl;
    }

    public void runLotto() {

    }
}
