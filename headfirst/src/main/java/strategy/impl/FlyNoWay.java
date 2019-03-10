package strategy.impl;

import strategy.FlyBehavior;

/**
 * Created by DINGJUN on 2019.03.09.
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞的鸭子");
    }
}
