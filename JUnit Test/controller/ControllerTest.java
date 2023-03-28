package controller;

import model.Fad;
import model.Lager;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
    }}