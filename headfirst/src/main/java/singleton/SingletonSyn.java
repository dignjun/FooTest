package singleton;

/**
 * 双重校验锁单利模式
 *
 * Created by DINGJUN on 2019.03.31.
 */
public class SingletonSyn {
    private SingletonSyn(){}
    private static volatile SingletonSyn singletonSyn;
    public static SingletonSyn getInstance(){
        if(singletonSyn == null) {
            synchronized (SingletonSyn.class) {
                if (singletonSyn == null){
                    singletonSyn = new SingletonSyn();
                }
            }
        }
        return singletonSyn;
    }
}
