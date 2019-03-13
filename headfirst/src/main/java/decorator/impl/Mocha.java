package decorator.impl;

import decorator.Beverage;
import decorator.CondimentDecorator;

/**
 * 装饰着具体实现
 *
 * Created by DINGJUN on 2019.03.13.
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
}
