package factory.abstract_;

/**
 * 用于提供依赖家族对象接口
 *
 * Created by DINGJUN on 2019.03.29.
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Clam createClam();
    Cheese createCheese();
}
