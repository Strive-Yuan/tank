package com.module.DesignMode.observer.push;

public class ForecastDisplay implements Observer, DisplayElement {
    private float temperature; //温度
    private float humidity; //湿度
    private Subject weatherData;

    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("Forecast Display " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
