package lotto;

import lotto.config.AppConfig;
import lotto.controller.LottoController;

/**
 * main 시작점
 */
public class Application {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

        LottoController lottoController = appConfig.settingLottoController();
        lottoController.runLotto();
    }
}
