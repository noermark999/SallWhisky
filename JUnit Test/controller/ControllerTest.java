package controller;

import model.Destillering;
import model.Fad;
import model.Lager;
import model.Paafyldning;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTest {

    @Test
    void TC1_updateLager() {
        //Arrange
        Lager lager = new Lager(3, "Lille Lager");
        Map<Integer, Fad> fade = new HashMap<>();
        fade.put(1,new Fad(1, "Bourbon", "Texas Whiskey", 100));


        Controller.updateLager(lager, 3, "Ny Lager");
        assertEquals(3, lager.getMaxPladser());
        assertEquals("Ny Lager", lager.getLagernavn());

    }
    @Test
    void TC2_updateLager() {

        //Arrange
        Lager lager =  new Lager(3, "Lille Lager");
        Map<Integer, Fad> fade = new HashMap<>();

        fade.put(1,new Fad(1,"Bourbon", "Kenneth", 50));
        fade.put(2,new Fad(2,"Sherry", "Kenneth", 50));
        fade.put(3,new Fad(3,"Rødvin", "Kenneth", 50));
        fade.put(4,new Fad(4,"Mezcal", "Kenneth", 50));


        // Act & Assert
    }

    @Test
    @Order(4)
    void TC1_fadeSomHarLagretI3Aar() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon", "Kenneth", 10);
        ArrayList<Fad> fads = new ArrayList<>();
        fads.add(fad);
        Storage.addFad(fad);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        destillering.createPaafyldning(10,LocalDate.of(2020,4,10),fad);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,11),fad);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,12),fad);

        //Act
        ArrayList<Fad> result = new ArrayList<>(Controller.fadeSomHarLagretI3Aar());

        //assert
        assertTrue(result.contains(fad));

    }
    @Test
    @Order(5)
    void TC2_fadeSomHarLagretI3Aar() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon", "Kenneth", 10);
        ArrayList<Fad> fads = new ArrayList<>();
        fads.add(fad);
        Storage.addFad(fad);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,10),fad);
        destillering.createPaafyldning(10,LocalDate.of(2020,4,11),fad);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,12),fad);

        //Act
        ArrayList<Fad> result = new ArrayList<>(Controller.fadeSomHarLagretI3Aar());

        //assert
        assertFalse(result.contains(fad));

    }
    @Test
    @Order(6)
    void TC3_fadeSomHarLagretI3Aar() {
        //Arrange
        Fad fad = new Fad(10,"Bourbon", "Kenneth", 10);
        ArrayList<Fad> fads = new ArrayList<>();
        fads.add(fad);
        Storage.addFad(fad);
        Destillering destillering = new Destillering(LocalDate.of(2019,4,9),LocalDate.of(2019,4,10),
                "Double Malt", "Byg","Lars",1000,73.8);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,10),fad);
        //destillering.createPaafyldning(10,LocalDate.of(2020,4,11),fad);
        destillering.createPaafyldning(10,LocalDate.of(2020,4,12),fad);

        //Act
        ArrayList<Fad> result = new ArrayList<>(Controller.fadeSomHarLagretI3Aar());

        //assert
        assertFalse(result.contains(fad));

    }

}