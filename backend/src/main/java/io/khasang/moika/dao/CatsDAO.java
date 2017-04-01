package io.khasang.moika.dao;

import io.khasang.moika.entity.Cats;

import java.util.List;

public interface CatsDAO {
    void addCat(Cats cat);
    void updateCat(Cats cat);
    List<Cats> getAllCats();
    Cats getCatById(long id);
    void deleteCatById(Cats cat);
    boolean containCatById(long id);
}
