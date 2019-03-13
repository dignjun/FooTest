package observe.impl_jdk;

import java.util.Observable;

/**
 * JDK中的观察者实现:可观察者
 *
 * Created by DINGJUN on 2019.03.12.
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    /**
     * 构造
     */
    public WeatherData(){}

    /**
     * 去掉了记住观察者而建立的数据结构
     * 在通知观察者前要setChanged()
     * 如果没有调用notifyObservers()"推"数据到观察者
     * 则表示我们采用的做法是"拉"数据
     */
    public void measurementsChanged(){
        setChanged();
        notifyObservers();
    }

    /**
     * 设置可观察者的数据变化
     * 设置数据的时候同时通知观察者
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
    public float getTemperature(){
        return temperature;
    }
    public float getHumidity(){
        return humidity;
    }
    public float getPressure(){
        return pressure;
    }
}
