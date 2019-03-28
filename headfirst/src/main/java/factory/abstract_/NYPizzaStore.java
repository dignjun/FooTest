package factory.abstract_;

import factory.Pizza;

/**
 * Created by DINGJUN on 2019.03.29.
 */
public class NYPizzaStore extends PizzaStore implements PizzaIngredientFactory {

    /**重写工厂方法*/
    @Override
    public Pizza createPizza(String type) {
        return null;
    }

    /**相关依赖对象的家族实例工厂方法*/
    @Override
    public Dough createDough() {
        return null;
    }

    /**相关依赖对象的家族实例工厂方法*/
    @Override
    public Clam createClam() {
        return null;
    }

    /**相关依赖对象的家族实例工厂方法*/
    @Override
    public Cheese createCheese() {
        return null;
    }
}
