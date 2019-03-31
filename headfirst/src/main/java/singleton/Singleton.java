package singleton;

/**
 * 单例模式
 *
 * Created by DINGJUN on 2019.03.29.
 */
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton(){}
    public static Singleton getInstance(){
        if(uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
