package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void addFad() {
        //Arrange
        Lager lager = new Lager(3,"Olivers baghave");
        Fad fad = new Fad(10,"Bourbon","Kenneth");
        Fad fad1 = new Fad(13,"Rødvin","Jonas");
        Fad fad2 = new Fad(17,"Æble Juice","Adam");
        Fad fad3 = new Fad(23,"Hvidvin","Scotti");

        //Act
        lager.addFad(fad,0);
        lager.addFad(fad1,1);
        lager.addFad(fad2,2);
        
        //Assert
        Exception forventet = assertThrows(RuntimeException.class, () -> {
                    lager.addFad(fad3,3);
                });
        assertEquals("Lageret har ikke mere plads." ,forventet.getMessage());
    }

    @Test
    void addFad1() {
        //Arrange


        //Act


        //Assert

    }
}