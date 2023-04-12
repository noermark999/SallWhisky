package model;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PaafyldningsTest {
    @Test
    @Order(1)
    void TC1_createPaafyldning() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Destillering destillering = new Destillering(LocalDate.now(), LocalDate.now(), "Maltetstuff","Kornsort","Ole",11.0, 50);

        //Act & Assert
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            destillering.createPaafyldning(11,LocalDate.now(),fad);
        });
        assertEquals("Påfyldningen må ikke overstige fadet!", forventet.getMessage());
    }

    @Test
    @Order(2)
    void TC2_createPaafyldning() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Destillering destillering = new Destillering(LocalDate.now(), LocalDate.now(), "Maltetstuff","Kornsort","Ole",11.0, 50);

        //Act
        destillering.createPaafyldning(1,LocalDate.now(),fad);

        //Assert
        assertEquals(1, destillering.getPaafyldninger().size());
    }

    @Test
    @Order(3)
    void TC3_createPaafyldning() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Destillering destillering = new Destillering(LocalDate.now(), LocalDate.now(), "Maltetstuff","Kornsort","Ole",11.0, 50);

        //Act
        destillering.createPaafyldning(10,LocalDate.now(),fad);

        //Assert
        assertEquals(1, destillering.getPaafyldninger().size());
    }
    @Test
    @Order(4)
    void TC1_omhaeldTilFad() {
        //Arrange
        Fad fad1 = new Fad(10,"Bourbon", "Kenneth", 50);
        Fad fad2 = new Fad(11,"Rød vin", "Lars", 50);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(50,LocalDate.of(2020,4,10),fad1);

        //Act
        for (Paafyldning p : fad1.getPaafyldninger()) {
            p.omhaeldTilFad(20,LocalDate.of(2020,4,15),fad2);
        }
        double expectedFad1 = 30;
        double expectedFad2 = 20;

        //Assert
        assertEquals(expectedFad1,fad1.getMaengdeTilbage());
        assertEquals(expectedFad2,fad2.getMaengdeTilbage());

    }
    @Test
    @Order(5)
    void TC2_omhaeldTilFad() {
        //Arrange
        Fad fad1 = new Fad(10,"Bourbon", "Kenneth", 50);
        Fad fad2 = new Fad(11,"Rød vin", "Lars", 50);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(50,LocalDate.of(2020,4,10),fad1);

        //Act
        for (Paafyldning p : fad1.getPaafyldninger()) {
            p.omhaeldTilFad(35,LocalDate.of(2020,4,15),fad2);
        }
        double expectedFad1 = 15;
        double expectedFad2 = 35;

        //Assert
        assertEquals(expectedFad1,fad1.getMaengdeTilbage());
        assertEquals(expectedFad2,fad2.getMaengdeTilbage());

    }
    @Test
    @Order(6)
    void TC3_omhaeldTilFad() {
        //Arrange
        Fad fad1 = new Fad(10,"Bourbon", "Kenneth", 50);
        Fad fad2 = new Fad(11,"Rød vin", "Lars", 50);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(50,LocalDate.of(2020,4,10),fad1);

        //Act
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            for (Paafyldning p : fad1.getPaafyldninger()) {
                p.omhaeldTilFad(100,LocalDate.of(2020,4,15),fad2);
            }
        });
        assertEquals("Den angivende mængde er mere end hvad der er i fadet" ,forventet.getMessage());

    }

    @Test
    @Order(7)
    void TC4_omhaeldTilFad() {
        //Arrange
        Fad fad1 = new Fad(10,"Bourbon", "Kenneth", 50);
        Fad fad2 = new Fad(11,"Rød vin", "Lars", 50);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(25,LocalDate.of(2020,4,10),fad1);
        destillering.createPaafyldning(25,LocalDate.of(2020,4,10),fad1);


        //Act
        for (Paafyldning p : fad1.getPaafyldninger()) {
            p.omhaeldTilFad(10,LocalDate.of(2020,4,15),fad2);
        }
        double expectedFad1 = 30;
        double expectedPaafyldning1 = 15;
        double expectedFad2 = 20;

        //Assert
        assertEquals(expectedFad1,fad1.getMaengdeTilbage());

        double actualPaafyldning1 = 0;
        for (Paafyldning p : fad1.getPaafyldninger()) {
            actualPaafyldning1 = p.getMaengde();
        }
        assertEquals(expectedPaafyldning1,actualPaafyldning1);

        assertEquals(expectedFad2,fad2.getMaengdeTilbage());

    }

    @Test
    @Order(8)
    void TC5_omhaeldTilFad() {
        //Arrange
        Fad fad1 = new Fad(10,"Bourbon", "Kenneth", 50);
        Fad fad2 = new Fad(11,"Rød vin", "Lars", 50);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(25,LocalDate.of(2020,4,10),fad1);
        destillering.createPaafyldning(25,LocalDate.of(2020,4,10),fad1);


        //Act
        for (Paafyldning p : fad1.getPaafyldninger()) {
            p.omhaeldTilFad(17.5,LocalDate.of(2020,4,15),fad2);
        }
        double expectedFad1 = 15;
        double expectedPaafyldning1 = 7.5;
        double expectedFad2 = 35;

        //Assert
        assertEquals(expectedFad1,fad1.getMaengdeTilbage());

        double actualPaafyldning1 = 0;
        for (Paafyldning p : fad1.getPaafyldninger()) {
            actualPaafyldning1 = p.getMaengde();
        }
        assertEquals(expectedPaafyldning1,actualPaafyldning1);

        assertEquals(expectedFad2,fad2.getMaengdeTilbage());

    }
}
