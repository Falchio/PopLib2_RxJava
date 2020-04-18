package ru.falchio.myrxjavaexample.observer;

public interface Observable<S> {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyAllObservers();
}
