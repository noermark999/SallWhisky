package model;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class PaafyldningsTest {
    //TC1: Mængde 11, Fadstørrelse 10,
    //TC2: Mængde 1, Fadstørrelse 10,
    //TC3: Mængde 10, Fadstørrelse 10

    @Test
    void TC1_createPaafyldning() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Destillering destillering = new Destillering(LocalDate.now(), LocalDate.now(), "Maltetstuff","Kornsort","Ole",11.0, 50);

        //Act & Assert
        RuntimeException forventet = assertThrows(IllegalArgumentException.class, () -> {
            destillering.createPaafyldning(11,LocalDate.now(),fad);
        });
        assertEquals("Der er for lidt plads i fadet", forventet.getMessage());
    }

    @Test
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
    void TC3_createPaafyldning() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Destillering destillering = new Destillering(LocalDate.now(), LocalDate.now(), "Maltetstuff","Kornsort","Ole",11.0, 50);

        //Act
        destillering.createPaafyldning(10,LocalDate.now(),fad);


        //Assert
        assertEquals(1, destillering.getPaafyldninger().size());
    }
}
