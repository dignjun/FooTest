package adapter;

import adapter.impl.MallardDuck;
import adapter.impl.TurkeyAdapter;
import adapter.impl.WildTurkey;

/**
 * 测试类
 *
 * Created by DINGJUN on 2019.04.01.
 */
public class DuckTestDrive {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();

        WildTurkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        System.out.println("the turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("the duck says...");
        testDuck(duck);

        System.out.println("the turkey adapter says...");
        testDuck(turkeyAdapter);
    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
