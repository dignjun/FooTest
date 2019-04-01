package adapter.impl;

import adapter.Turkey;

/**
 * 野鸡实现
 * Created by DINGJUN on 2019.04.01.
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("i am flying a short instance");
    }
}
