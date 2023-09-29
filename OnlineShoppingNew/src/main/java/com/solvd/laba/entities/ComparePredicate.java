package com.solvd.laba.entities;

@FunctionalInterface
public interface ComparePredicate<T> {
    boolean compare(T t);
}
