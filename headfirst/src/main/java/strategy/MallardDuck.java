package strategy;

import strategy.impl.FlyWithWings;
import strategy.impl.Quack;

/**
 * 鸭子类的具体实现，如果是行为上有特别的要求，
 * 那么只需要调用父类中的委托行为的方法就可以了
 * Created by DINGJUN on 2019.03.09.
 */
public class MallardDuck extends Duck {
    public MallardDuck(){
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }
    public void display(){
        performFly();
        System.out.println("i am a mallard duck");
        performQuack();
    }
}
