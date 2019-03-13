package decorator.impl;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * Created by DINGJUN on 2019.03.13.
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return 0.34 + beverage.cost();
    }
}
