package ru.ramprox.optimizing;

import ru.ramprox.optimizing.car.BasicFunctionalCar;
import ru.ramprox.optimizing.car.CarFactory;

public class Main {
    public static void main(String[] args) {
        BasicFunctionalCar lightWeightCar = CarFactory.createLightWeightCar();
        lightWeightCar.open();
        lightWeightCar.start();
        lightWeightCar.move();
        lightWeightCar.stop();

        BasicFunctionalCar lorryCar = CarFactory.createLorryCar();
        lorryCar.open();
        lorryCar.start();
        lorryCar.move();
        lorryCar.stop();
    }
}
