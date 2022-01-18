package ru.ramprox.optimizing.car;

import ru.ramprox.optimizing.engine.DieselEngine;

public class CarFactory {
    public static BasicFunctionalCar createLightWeightCar() {
        return new LightWeightCar(new DieselEngine(), "Red", "Light weight");
    }

    public static BasicFunctionalCar createLorryCar() {
        return new Lorry(new DieselEngine(), "Black", "Lorry");
    }
}
