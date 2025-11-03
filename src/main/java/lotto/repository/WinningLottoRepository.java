package lotto.repository;


import lotto.domain.WinningLotto;
import java.util.List;

public interface WinningLottoRepository {
    WinningLotto save(WinningLotto winningLotto);
    List<WinningLotto> findAll();
    void clear();
}
