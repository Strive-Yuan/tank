package com.module.DesignMode.observer.push;

public interface Subject {

    void registerObserver(Observer... observer);

    void removeObserver(Observer observer);

    void notifyObserver();
}
