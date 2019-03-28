package factory.method;

import factory.Pizza;

/**
 * Created by DINGJUN on 2019.03.28.
 */
public class ChicagoStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if(type.equals("clam")) {
            // chicago clam pizza
        }
        return null;
    }
}
