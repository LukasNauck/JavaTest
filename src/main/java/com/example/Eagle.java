package com.example;

public class Eagle extends Animal implements FlyingAnimal{

    Eagle() {
        super(90, 120, 30, 50, "Eagle");
    }

    public void makeSound() {
        System.out.println("Ka-Kaw, Ka-Kaw.");
    }

    @Override
    public void fly() {
        this.weight = this.weight - 5;        
    }
}