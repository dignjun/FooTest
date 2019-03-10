package strategy.impl;

import strategy.QuackBehavior;

/**
 * Created by DINGJUN on 2019.03.09.
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
