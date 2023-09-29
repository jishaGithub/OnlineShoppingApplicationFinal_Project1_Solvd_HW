package com.solvd.laba.entities;

@FunctionalInterface
public interface TwoNumberCalculationFunction<T,U,R> {
    R doCalculate(T t, U u);
}
