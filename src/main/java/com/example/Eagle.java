package com.example;

public class Eagle extends Animal{

    Eagle(){
        super(90, 120,30,50,"Eagle");
    }

    public void makeSound(){
        System.out.println("Ka-Kaw, Ka-Kaw.");
    }
}