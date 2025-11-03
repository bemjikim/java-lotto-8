package lotto.repository;

import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.List;

public class MemoryWinningLottoRepository implements WinningLottoRepository {
    private static List<WinningLotto> winningLottoStorage = new ArrayList<>();

    @Override
    public WinningLotto save(WinningLotto winningLotto) {
        winningLottoStorage.add(winningLotto);
        return winningLotto;
    }

    @Override
    public List<WinningLotto> findAll() {
        return winningLottoStorage;
    }

    @Override
    public void clear() {
        winningLottoStorage.clear();
    }
}
