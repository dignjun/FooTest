package command;

/**
 * object 对象
 * <p>
 * Created by DINGJUN on 2019.03.31.
 */
public class Light {

    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + " light on");
    }

    public void off(){
        System.out.println(name + " light off");
    }
}
