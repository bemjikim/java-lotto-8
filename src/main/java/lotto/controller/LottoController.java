package lotto.controller;

import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.enums.ErrorMessage;
import lotto.repository.MemoryPurchaseLottoRepository;
import lotto.repository.MemoryWinningLottoRepository;
import lotto.service.PurchaseLottoService;
import lotto.service.WinningLottoService;
import lotto.util.LottoNumberGeneratorImpl;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoController {
    private final static int TRYCOUNT = 0;
    private final static String DELIMITER = ",";
    private InputView inputView;
    private OutputView outputView;
    private LottoNumberGeneratorImpl lottoNumberGeneratorImpl;
    private MemoryPurchaseLottoRepository memoryPurchaseLottoRepository;
    private MemoryWinningLottoRepository memoryWinningLottoRepository;

    public LottoController(InputView inputView, OutputView outputView, LottoNumberGeneratorImpl lottoNumberGeneratorImpl, MemoryPurchaseLottoRepository memoryPurchaseLottoRepository, MemoryWinningLottoRepository memoryWinningLottoRepository) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoNumberGeneratorImpl = lottoNumberGeneratorImpl;
        this.memoryPurchaseLottoRepository = memoryPurchaseLottoRepository;
        this.memoryWinningLottoRepository = memoryWinningLottoRepository;
    }

    /**
     * 로또 프로그램 실행 (돈 입력 -> 로또 구매 및 출력 -> 당첨번호 및 보너스 번호 추첨 -> 결과 출력)
     */
    public void runLotto() {
        Money money = initializeMoney();

        PurchaseLottoService purchaseLottoService = new PurchaseLottoService(memoryPurchaseLottoRepository);
        purchaseAndPrintLotto(money, purchaseLottoService);

        WinningLotto winningLotto = initializeWinningLotto();
        WinningLottoService winningLottoService = new WinningLottoService(memoryWinningLottoRepository);
        winningLottoService.enrollWinningLotto(winningLotto);

        printLottoResult(money, purchaseLottoService, winningLottoService);
    }

    /**
     * 로또 구매를 위한 금액 입력하는 함수 (입력 후 예외 발생시 재귀함수를 통해 재입력)
     */
    private Money initializeMoney() {
        try {
            int moneyInput = inputView.readWithValidateCashInput();
            Money money = new Money(moneyInput, TRYCOUNT);
            return money;

        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return initializeMoney();
        }
    }

    /**
     * 로또를 구입해서 로또 목록을 보여주는 함수
     *
     * @param money: 사용자가 입력한 돈 객체
     * @param purchaseLottoService: 로또를 구매, 찾을 수 있는 서버스
     */
    private void purchaseAndPrintLotto(Money money, PurchaseLottoService purchaseLottoService) {
        try {
            purchaseLottoService.purchaseLotto(money, lottoNumberGeneratorImpl);
            purchaseLottoService.printPurchaseLotto(money);

        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            purchaseAndPrintLotto(money, purchaseLottoService);
        }
    }

    /**
     * 당첨번호 및 보너스 번호 입력 함수 (입력 후 예외 발생시 재귀함수를 통해 재입력)
     */
    private WinningLotto initializeWinningLotto() {
        try {
            String number = inputView.readWithValidateNumberInput();
            int bonusNumber = inputView.readWithValidateBonusNumberInput();
            List<Integer> winningLottoNumber = assembleWinningNumberAndBonusNumber(number, bonusNumber);

            WinningLotto winningLotto = new WinningLotto(winningLottoNumber);

            return winningLotto;

        } catch (IllegalArgumentException e) {
            outputView.printException(e);
            return initializeWinningLotto();
        }
    }

    /**
     * 당첨번호 및 보너스 번호를 하나의 리스트로 병합하는 함수
     *
     * @param number: 사용자가 입력한 당첨 번호
     * @param bonusNumber: 사용자가 입력한 보너스 번호
     *
     * @return: 당첨번호 + 보너스번호가 담긴 winningLottoNumber 리스트 반환
     */
    private List<Integer> assembleWinningNumberAndBonusNumber(String number, int bonusNumber) {
        List<Integer> winningLottoNumber = new ArrayList<>(splitAndValidate(number));
        winningLottoNumber.add(bonusNumber);

        return winningLottoNumber;
    }

    /**
     * 당첨번호를 구분자 기준으로 분리해서 리스트로 변환하는 함수
     *
     * @param number: 사용자가 입력한 당첨 번호
     *
     * @return: 당첨번호가 담긴 리스트 반환
     */
    private List<Integer> splitAndValidate(String number) {
        try {
            return Arrays.stream(number.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_WINNING_LOTTO_NUMBER_FORMAT.getMessage() + number);
        }
    }

    /**
     * 당첨번호와 구매한 로또 번호를 비교하여 결과를 출력하는 함수
     *
     * @param money: 사용자가 입력한 금액
     * @param purchaseLottoService: 사용자가 구매한 구매로또를 관리하는 서비스 객체
     * @param winningLottoService: 사용자가 입력한 당첨번호를 관리하는 서비스 객체
     */
    private void printLottoResult(Money money, PurchaseLottoService purchaseLottoService, WinningLottoService winningLottoService) {
        List<Integer> compareResult = winningLottoService.compareLotto(purchaseLottoService);
        winningLottoService.printWinningLottoResult(money, compareResult);
    }
}
