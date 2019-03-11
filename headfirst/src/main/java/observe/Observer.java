package observe;

/**
 * 观察者模式的观察者接口
 * <p> 所有的观察者必须实现update（）方法，以实现观察者接口
 * Created by DINGJUN on 2019.03.11.
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
