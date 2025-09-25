package com.example;

public class Dolphin extends Animal{

    Dolphin(){
        super(900, 1500,10,35,"Dolphin");
    }

    public void makeSound(){
        System.out.println("Eeh-eh-eh-he.");
    }
}