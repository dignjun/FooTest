package singleton;

/**
 * 饿汉式
 *
 * Created by DINGJUN on 2019.03.31.
 */
public class SingletonStatic {
    private static SingletonStatic uniqueInstance = new SingletonStatic();
    private SingletonStatic(){}
    public static SingletonStatic getUniqueInstance(){
        return uniqueInstance;
    }
}
