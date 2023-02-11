package com.module.DesignMode.observer.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeatherData implements Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private float temperature; //温度
    private float humidity; //湿度
    private float pressure; //压力

    public WeatherData() {
    }

    @Override
    public void registerObserver(Observer... observers) {
        this.observers.addAll(Arrays.asList(observers));
    }


    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        System.out.println("i:" + i);
        if (i > 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (int i = 0; i < observers.size(); i++) {
            Observer obs = observers.get(i);
            obs.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObserver();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
