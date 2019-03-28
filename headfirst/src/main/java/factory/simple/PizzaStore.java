package factory.simple;

import factory.Pizza;

/**
 * 简单工厂客户端类
 * 客户端类  -->  依赖工厂类 -->  实体
 * Created by DINGJUN on 2019.03.27.
 */
public class PizzaStore {

    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
