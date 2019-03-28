package factory.abstract_;

import factory.Pizza;

/**
 * Created by DINGJUN on 2019.03.29.
 */
public abstract class PizzaStore {
    // other method
    public abstract Pizza createPizza(String type);
}
