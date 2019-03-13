package decorator;

/**
 * 饮料超类
 * <p>
 * Created by DINGJUN on 2019.03.13.
 */
public abstract class Beverage {
    public String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
