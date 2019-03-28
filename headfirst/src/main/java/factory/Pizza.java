package factory;

import java.util.ArrayList;

/**
 * 抽象类
 *
 * Created by DINGJUN on 2019.03.27.
 */
public class Pizza {
    public String name;
    public String dough;
    public String sauce;
    public ArrayList toppings = new ArrayList<>();

    public void prepare() {
        System.out.println("preparing " + name);
        System.out.println("tossing dough " + dough);
        System.out.println("adding sauce " + sauce);
        System.out.println("adding toppings: ");
        for (int i=0;i<toppings.size(); i++) {
            System.out.println("    " + toppings.get(i));
        }
    }
    public void bake(){
        System.out.println("bake 30 min");
    }
    public void cut(){
        System.out.println("cutting the pizza into slices");
    }
    public void box(){
        System.out.println("place into box");
    }
}
