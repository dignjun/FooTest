package adapter.impl;

import adapter.Duck;

/**
 * 绿头鸭实现
 * Created by DINGJUN on 2019.04.01.
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("quack");
    }

    @Override
    public void fly() {
        System.out.println("i am flying");
    }
}
