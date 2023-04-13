package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlaskeTest {

    @Test
    void TC1_addFad() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Jonathan", 30);


        //Act & Assert
        Exception forventet = assertThrows(IllegalArgumentException.class, () -> {
            Flaske flaske1 = new Flaske("Inagural", LocalDate.parse("2018-10-23"),30.0,5,10.0, "Kildevand","Whisky fra vores første fad",50.0,fad);
        });
        assertEquals(forventet.getMessage(), "Kan ikke tilføje fad da flasken ikke har plads til den angivende mængde");
    }

    @Test
    void TC2_addFad() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Jonathan", 30);
        Destillering destillering = new Destillering(LocalDate.parse("2018-09-23"),LocalDate.parse("2018-09-24"),"Single malt", "Kornsort", "Troels",1000.0,40.0);
        destillering.createPaafyldning(30,LocalDate.parse("2018-09-25"),fad);
        Flaske flaske2 = new Flaske("Inagural", LocalDate.parse("2018-10-23"),40.0,70,10.0, "Kildevand","Whisky fra vores første fad",10,fad);


        //Act
        flaske2.addFad(0.7,fad);

        //Assert
        assertEquals(10,flaske2.getFade().get(fad));
    }

    @Test
    void TC1_samletMaengde() {
        //Arrange
        Destillering destillering = new Destillering(LocalDate.of(2018,10,10), LocalDate.of(2018,10,12), "Maltbatch", "kornsort", "Medarbejder", 1000, 70.0);
        Fad fad1 = new Fad(20, "Fadtype", "leverandør", 150);
        destillering.createPaafyldning(150, LocalDate.of(2018,10,13),fad1);
        Flaske flaske1 = new Flaske("Flaske1", LocalDate.now(), 50.0,70.0,20.0,"Vandtype", "beskrivelse", 50.0, fad1);

        //Act
        double actualMaengde = flaske1.samletMaengde();
        double expectedMaengde = 50.0;

        //Assert
        assertEquals(expectedMaengde, actualMaengde);
    }

    @Test
    void TC2_samletMaengde() {
        Destillering destillering = new Destillering(LocalDate.of(2018,10,10), LocalDate.of(2018,10,12), "Maltbatch", "kornsort", "Medarbejder", 1000, 70.0);
        Fad fad1 = new Fad(20, "Fadtype", "leverandør", 150);
        Fad fad2 = new Fad(21, "FadType", "leverandør", 200);
        destillering.createPaafyldning(150, LocalDate.of(2018,10,13),fad1);
        destillering.createPaafyldning(200, LocalDate.of(2018,10,13),fad2);
        Flaske flaske2 = new Flaske("Flaske1", LocalDate.now(), 42.3,60.0,10.0,"Vandtype", "beskrivelse", 25.0, fad1);
        flaske2.addFad(25.0, fad2);

        //Act
        double actualMaengde = flaske2.samletMaengde();
        double expectedMaengde = 50.0;

        //Assert
        assertEquals(expectedMaengde, actualMaengde);
    }
}