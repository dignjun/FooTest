package factory.method;

import factory.Pizza;

/**
 * Created by DINGJUN on 2019.03.27.
 */
public class NYStyleCheesePizza extends Pizza {
    public NYStyleCheesePizza(){
        name = "ny style sauce and cheese pizza";
        dough = "thin crust dough";
        sauce = "marinara sauce";

        toppings.add("grated reggiano cheese");
    }
}
