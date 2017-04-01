package io.khasang.moika.service;

import io.khasang.moika.entity.Orderm;

import java.util.List;

public interface OrdermAccessService {
    Orderm addOrderm(Orderm orderm);
    Orderm updateOrderm(Orderm orderm);
    void deleteOrderm(Orderm orderm);
    Orderm getOrderm(long id);
    List<Orderm> getAllOrderm();
}
