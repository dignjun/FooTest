package factory.method;

import factory.Pizza;

/**
 * 工厂方法的引入
 * 允许子类做决定
 * <p>
 *
 * Created by DINGJUN on 2019.03.27.
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 生成对象的方法
     *
     * @param type
     * @return
     */
    abstract Pizza createPizza(String type);
}
