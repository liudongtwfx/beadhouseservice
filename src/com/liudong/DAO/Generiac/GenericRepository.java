package com.liudong.DAO.Generiac;

import com.sun.istack.NotNull;

import java.io.Serializable;

/**
 * Created by liudong on 2017/1/5.
 */
public interface GenericRepository<I extends Serializable, E extends Serializable> {
    @NotNull
    Iterable<E> getall();

    E get(@NotNull I id);

    void add(@NotNull E entity);

    void update(@NotNull E entity);

    void delete(@NotNull E entity);

    void deleteById(@NotNull I id);

}
