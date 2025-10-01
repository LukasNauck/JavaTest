package com.example;

public class Fish extends Animal implements SwimmingAnimal {

    public Fish() {
        super(20, 20, 5, 5, "Fish");
    }

    @Override
    public void makeSound() {
        System.out.println("Blub blub.");
    }

    @Override
    public void swim() {
        this.weight = this.weight - 1;
    }

}
