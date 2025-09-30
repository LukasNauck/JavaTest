package com.example;

public class Ape extends Animal implements ClimbingAnimal{

    Ape() {
        super(90, 150, 3, 7, "Ape");
    }

    public void makeSound() {
        System.out.println("Ooh ooh ah Ahh!");
    }

    @Override
    public void climb() {
        this.weight = this.weight - 3;
    }
}