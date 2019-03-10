package strategy.impl;

import strategy.QuackBehavior;

/**
 * Created by DINGJUN on 2019.03.09.
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
