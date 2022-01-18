package ru.ramprox.optimizing.car;

import ru.ramprox.optimizing.engine.Engine;

class LightWeightCar extends Car {

    public LightWeightCar(Engine engine, String color, String name) {
        super(engine, color, name);
    }

    @Override
    public void open() {
        System.out.println("Car is open");
    }

}
