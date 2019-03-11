package observe;

/**
 * 观察者模式的主题对象
 * <p>
 * Created by DINGJUN on 2019.03.11.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();
}
