package ru.ramprox.optimizing.car;

import ru.ramprox.optimizing.engine.Engine;

// Синтаксическая ошибка в объявлении (для интерфейсов нужно писать implements, а не extends)
class Lorry extends Car implements BasicFunctionalCar {

    public Lorry(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    // Переопределение метода open обязательно
    @Override
    public void open() {
        System.out.println("Lorry car is open");
    }
}
