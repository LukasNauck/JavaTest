package com.example;
import java.util.Scanner;

public class Menue{
    
    static ZooManager zooManager = new ZooManager();
    
    public static void main(String[] args){
        printOptions();
        handleChoice();
    }


    public static void handleChoice(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please make a choice");
        int choice = scanner.nextInt();

        do {
            switch (choice){
                case 1 :
                    zooManager.createApe();
                    break;
                case 2 : 
                    zooManager.createBear();
                    break;
                case 3 :
                    zooManager.createDolphin();
                    break;
                case 4 : 
                    zooManager.createEagle();
                    break;
                case 5 : 
                    zooManager.printAllAnimals();
                    break;
                case 6 : 
    
                case 7 :
                    System.out.println("Noch nicht implementiert.");           
                    break;
                default: 
                    System.out.println("Invalid choice.");
                    break;
            }
            System.out.println("Please make a choice");
            choice = scanner.nextInt();

        } while (choice!=8);
        
    }
        
        
    public static void printOptions(){
        System.out.println("Select an option!");
        System.out.println("1. Get an Ape");
        System.out.println("2. Get a Bear");
        System.out.println("3. Get an Eagle");
        System.out.println("4. Get a Dolphin");
        System.out.println("5. List all animals and their weights");
        System.out.println("6. Feed an animal (not implemented yet)");
        System.out.println("7. Feed all animals (not implemented yet)");
        System.out.println("8. Terminate program");
    }
    

}