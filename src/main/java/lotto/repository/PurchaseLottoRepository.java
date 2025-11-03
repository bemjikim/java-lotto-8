package lotto.repository;

import lotto.domain.Lotto;

import java.util.List;

public interface PurchaseLottoRepository {
    Lotto save(Lotto lotto);
    List<Lotto> findAll();
    void clear();
}
