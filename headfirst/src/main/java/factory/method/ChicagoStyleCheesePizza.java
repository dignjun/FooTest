package factory.method;

import factory.Pizza;

/**
 * Created by DINGJUN on 2019.03.27.
 */
public class ChicagoStyleCheesePizza extends Pizza {
    public ChicagoStyleCheesePizza(){
        name = "chicago style deep dish cheese pizza";
        dough = "extra thick crust dough";
        sauce = "plum tomato sauce";
        toppings.add("shredded mozzarella cheese");
    }
}
