package com.example;

public class Bear extends Animal implements ClimbingAnimal{

    Bear() {
        super(2000, 2600, 30, 90, "Bear");
    }

    public void makeSound() {
        System.out.println("Grrrr!");
    }

    @Override
    public void climb() {
        this.weight = this.weight - 30;
    }
}