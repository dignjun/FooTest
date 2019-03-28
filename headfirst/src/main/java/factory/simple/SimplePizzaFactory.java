package factory.simple;

import factory.*;

/**
 * 简单工厂
 * 将创建对象的职责全部集中管理
 * 1.可以减少错误的发生,易于测试
 * 2.可以复用
 * <p>
 *    简单工厂模式依赖关系:PizzaStore --> SimplePizzaFactory --> Pizza
 *
 * Created by DINGJUN on 2019.03.27.
 */
public class SimplePizzaFactory {

    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if (type.equals("cheese")) {
            pizza = new ChessPizza();
        } else if (type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if (type.equals("clam")) {
            pizza = new ClamPizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }

        return pizza;
    }
}
