package strategy.impl;

import strategy.FlyBehavior;

/**
 * Created by DINGJUN on 2019.03.09.
 */
public class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("可以飞行的飞行鸭");
    }
}
