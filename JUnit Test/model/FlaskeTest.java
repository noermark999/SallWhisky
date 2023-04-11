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
    void samletMaengde() {
    }
}