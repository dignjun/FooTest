package factory;

import factory.method.ChicagoStore;
import factory.method.NYPizzaStore;

/**
 * Created by DINGJUN on 2019.03.28.
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        NYPizzaStore nyPizzaStore = new NYPizzaStore();
        ChicagoStore chicagoStore = new ChicagoStore();

        Pizza pizza = nyPizzaStore.orderPizza("cheese");
        System.out.println(pizza.name);

        System.out.println("--------------------");

        Pizza pizza1 = chicagoStore.orderPizza("cheese");
        System.out.println(pizza1.name);
    }
}
