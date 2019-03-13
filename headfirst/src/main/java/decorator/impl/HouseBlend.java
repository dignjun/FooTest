package decorator.impl;

import decorator.Beverage;

/**
 * 饮料具体实现
 * <p>
 * Created by DINGJUN on 2019.03.13.
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
