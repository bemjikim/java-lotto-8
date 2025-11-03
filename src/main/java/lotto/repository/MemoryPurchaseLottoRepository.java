package lotto.repository;

import lotto.domain.Lotto;

import java.util.ArrayList;
import java.util.List;

public class MemoryPurchaseLottoRepository implements PurchaseLottoRepository {
    private static List<Lotto> purchaseLottoStorage = new ArrayList<>();

    @Override
    public Lotto save(Lotto lotto) {
        purchaseLottoStorage.add(lotto);
        return lotto;
    }

    @Override
    public List<Lotto> findAll() {
        return purchaseLottoStorage;
    }

    @Override
    public void clear() {
        purchaseLottoStorage.clear();
    }
}
