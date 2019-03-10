package strategy;

/**
 * 鸭子顶级父类，含有基本的行为，游泳、演示。
 * 叫和飞行为是变化的行为，即不同的鸭子的行为是不一样的（对比同一个算法的不同实现）。
 * 封装变化，通过组合行为的顶级接口，然后将相应的行为委托给相应行为的顶级接口，
 * 如果鸭子子类在初始化的时候给出具体的行为实现，那么这个行为的不同，就实现了
 * 动态改变。
 * Created by DINGJUN on 2019.03.09.
 */
public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {
    }
    // 委托行为给行为抽象
    public void performQuack() {
        quackBehavior.quack();
    }
    public void swim() {
        System.out.println("游泳");
    }
    public void display() {
        System.out.println("演示");
    }
    // 委托行为给行为抽象
    public void performFly() {
        flyBehavior.fly();
    }
}
