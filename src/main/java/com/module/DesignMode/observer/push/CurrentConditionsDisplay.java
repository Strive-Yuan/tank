package com.module.DesignMode.observer.push;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature; //温度
    private float humidity; //湿度
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
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
        System.out.println("Current Conditions " + temperature + "F degrees and " + humidity + "% humidity");
    }
}
