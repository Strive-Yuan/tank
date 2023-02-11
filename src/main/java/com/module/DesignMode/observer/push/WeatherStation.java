package com.module.DesignMode.observer.push;

/**
 * 观察者模式测试程序
 */
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay conditionsDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        weatherData.registerObserver(conditionsDisplay, statisticsDisplay, forecastDisplay);

        weatherData.setMeasurements(80, 65, 30.4F);

        System.out.println("-----------------------------------");
        weatherData.removeObserver(forecastDisplay);
        weatherData.measurementsChanged();

    }
}
