package factory.method;

import factory.Pizza;

/**
 *
 * Created by DINGJUN on 2019.03.27.
 */
public class NYPizzaStore extends PizzaStore{

    /**
     * 重写的工厂方法
     *
     * @param type pizza类型
     * @return pizza对象
     */
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")){
            return new NYStyleCheesePizza();
        } else if(type.equals("clam")){
            return new NYStyleClamPizza();
        }
        return null;
    }
}
