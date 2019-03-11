package observe;

import observe.impl.CurrentConditionsDisplay;
import observe.impl.WeatherData;

/**
 * 测试类
 *
 * Created by DINGJUN on 2019.03.11.
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(23, 34, 55.5f);
    }
}
