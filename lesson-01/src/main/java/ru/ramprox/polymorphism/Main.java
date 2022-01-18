package ru.ramprox.polymorphism;

import ru.ramprox.polymorphism.shape.Shape;
import ru.ramprox.polymorphism.shape.Triangle;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Triangle();
        shape.draw();
    }
}
