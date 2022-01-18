package ru.ramprox.optimizing.car;

import ru.ramprox.optimizing.engine.Engine;

// 1. Если Car имеет двигатель (Engine) и объявлен метод start() (запуск машины),
//    то должен быть и метод stop(), так как запуск машины связан с запуском двигателя.
//    Странно, если машину можно будет запустить, но нельзя будет остановить.
//    Интерфейс Stopable содержит единственный метод stop(). В него нужно добавить метод start()
//    и соответственно переименовать его, например в StartStopable. Класс Car будет реализовывать
//    интерфейс StartStopable.
// 2. Обе реализации класса Car (LightWeightCar и Lorry) реализуют интерфейс Moveable. Можно
//    перенести объявление и реализацию по умолчанию интерфейса Moveable в класс Car.
// 3. Объявлен абстрактный метод open(). То есть все машины обязательно должны открываться, но
//    открываются они по-разному. Этот метод тоже лучше перенести в интерфейс (Например, Openable).
//    И объявление метода open() можно убрать.
// 4. Нужно создать некий интерфейс, объединяющий эти интерфейсы (StartStopable, Openable, Moveable),
//    представляющий базовую общую функциональность для всех машин (Например, BasicFunctionalCar)
//    через который удобно работать с этими интерфейсами.
abstract class Car implements BasicFunctionalCar {
    // Поле необходимо сделать private и объявить сущность Engine
    private Engine engine;
    private String color;
    private String name;

    // Необходим конструктор для удобной инициализации объектов Car
    public Car(Engine engine, String color, String name) {
        this.engine = engine;
        this.color = color;
        this.name = name;
    }

    // Метод start лучше сделать public,
    // чтобы его можно было запустить извне данного объекта Car.
    @Override
    public void start() {
        System.out.println("Car starting");
    }

    @Override
    public void stop() {
        System.out.println("Car is stop");
    }

    @Override
    public void move() {
        System.out.println("Car is moving");
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
