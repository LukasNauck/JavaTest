package com.example;

public class Student {
    String name;
    int matrikelnummer=0;
    static int studentenanzahl=0;

    public void getName() {
        System.out.println(name);
    }

    public void getMatrikelnummer() {
        System.out.println(matrikelnummer);
    }

    public void setName(String neuerName) {
        name = neuerName;
    }

    public void setMatrikelnummer(int neueMatrikelnummer) {
        matrikelnummer = neueMatrikelnummer;

    }
    //Konstruktoren
    public Student(){
        studentenanzahl = studentenanzahl + 1;
        this.matrikelnummer = matrikelnummer;
        this.name = name;
    }
    //Konstruktor mit Übergabe von M.Nummer und Name
    public Student(int matrikelnummer, String name){
        studentenanzahl = studentenanzahl + 1;
        this.matrikelnummer = matrikelnummer;
        this.name = name;
    }

    public static void main(String[] args) {

    //Studenten erstellen
    Student student = new Student(1,"Hans");
    Student student2 = new Student();
    Student student3 = new Student();
    Student student4 = new Student();

    //Daten für Student 2 setzen
    student2.setMatrikelnummer(2);
    student2.setName("Peter");

    //Daten von Studenten ausgeben
    System.out.println("Studentmatrikelnummer: "+student.matrikelnummer);
    System.out.println("Student2matrikelnummer: "+student2.matrikelnummer);
    System.out.println("Studentname: "+student.name);
    System.out.println("Student2name: "+student2.name);

    //Studentenanzahl ausgeben
    System.out.println("Studentenanzahl: "+Student.studentenanzahl);

        }
}