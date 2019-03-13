package decorator.impl;

import decorator.Beverage;

/**
 * 饮料具体实现
 *
 * Created by DINGJUN on 2019.03.13.
 */
public class Espresso extends Beverage {
    public Espresso(){
        description = "Espresso";
    }
    @Override
    public double cost() {
        return 0.13;
    }
}
