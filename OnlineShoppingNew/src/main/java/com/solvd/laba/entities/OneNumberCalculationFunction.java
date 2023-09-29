package com.solvd.laba.entities;

@FunctionalInterface
public interface OneNumberCalculationFunction<T> {
    T calculate(T t);
}
