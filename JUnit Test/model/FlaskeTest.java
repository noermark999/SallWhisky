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
            Flaske flaske = new Flaske("Inagural", LocalDate.now(),30.0,5,10.0, "Kildevand","Whisky fra vores første fad",50.0,fad);
        });
        assertEquals(forventet.getMessage(), "Kan ikke tilføje fad da flasken ikke har plads til den angivende mængde");
    }

    @Test
    void TC2_addFad() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Jonathan", 30);
        Flaske flaske = new Flaske("Inagural", LocalDate.now(),30.0,5,10.0, "Kildevand","Whisky fra vores første fad",50.0,fad);

        //Act
        flaske.addFad(20,fad);

        //Assert
        assertEquals(20,flaske.getFade().get(fad));
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

    }
}