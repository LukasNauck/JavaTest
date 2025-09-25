
package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ZooManagerTest {

    @Test
    void creatingOneAnimalOfAKindMeans4Animals(){
        //Arrange
        var zooManager = new ZooManager();
        //Act
        zooManager.createApe();
        zooManager.createDolphin();
        zooManager.createBear();
        zooManager.createEagle();
        //Assert (Anzahl an Tieren ist 4)
        assertEquals(4, zooManager.animals.size());
    }
 
    @Test
    void sortedInsertWithEmptyListShouldReturnZero() {
        var zooManager = new ZooManager();

        assertEquals(0, zooManager.sortedInsert("Bear"));
    }

    @Test
    void sortedInsert_insertDuplicateType() {
        var zooManager = new ZooManager();

        zooManager.createBear();
        zooManager.createBear();
        zooManager.createBear();
        
        assertEquals(3, zooManager.sortedInsert("Bear"));
    }
    
     
   
    @Test 
    void ApesShouldBePutInFront(){
        
        var zooManager = new ZooManager();
        
        zooManager.createBear();
        zooManager.createDolphin();
        zooManager.createDolphin();
        zooManager.createEagle();
        zooManager.createApe();
        zooManager.createEagle();

        assertTrue("Ape" == zooManager.animals.get(0).getAnimalType());
    }

    @Test 
    void EaglesShouldBePutLast(){
        var zooManager = new ZooManager();
        
        zooManager.createEagle();
        zooManager.createBear();
        zooManager.createDolphin();
        zooManager.createDolphin();
        zooManager.createEagle();
        zooManager.createApe();
        zooManager.createEagle();

        assertTrue("Eagle" == zooManager.animals.get(4).getAnimalType());
        assertTrue("Eagle" == zooManager.animals.get(5).getAnimalType());
        assertTrue("Eagle" == zooManager.animals.get(6).getAnimalType());
    } 
    
}