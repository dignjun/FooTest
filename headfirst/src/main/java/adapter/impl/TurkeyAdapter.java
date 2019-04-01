package adapter.impl;

import adapter.Duck;
import adapter.Turkey;

/**
 * 我们需要鸭子对象,但是只能使用火鸡对象替代
 * 所以使用一个火鸡的适配器,做一个类型替换.
 * Created by DINGJUN on 2019.04.01.
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;
    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }
    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        // 因为火鸡飞行的距离较短,所以这里需要修饰适配到鸭子的飞行距离
        for (int i = 0; i < 5; i ++) {
            turkey.fly();
        }
    }
}
