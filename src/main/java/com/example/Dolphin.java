package com.example;

public class Dolphin extends Animal implements SwimmingAnimal {

    Dolphin() {
        super(900, 1500, 10, 35, "Dolphin");
    }

    public void makeSound() {
        System.out.println("Eeh-eh-eh-he.");
    }

    @Override
    public void swim() {
        this.weight = this.weight - 3;
    }
}