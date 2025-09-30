package com.example;

public class Bat extends Animal implements FlyingAnimal {

    public Bat() {
        super(50, 100, 6, 20, "Bat");
    }

    @Override
    public void makeSound() {
        System.out.println("Squeek.");
    }

    @Override
    public void fly() {
        this.weight = this.weight - 2;
    }

}
