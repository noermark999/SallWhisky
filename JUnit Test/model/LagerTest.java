package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LagerTest {

    @Test
    void TC1_addFad() {
        //Arrange
        Lager lager = new Lager(3,"Olivers baghave");
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Fad fad1 = new Fad(13,"Rødvin","Jonas", 25);
        Fad fad2 = new Fad(17,"Æble Juice","Adam", 50);
        Fad fad3 = new Fad(23,"Hvidvin","Scotti", 15);

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
    void TC2_addFad() {
        //Arrange
        Lager lager = new Lager(3,"Jakobs Kælder");
        Fad fad = new Fad(10,"Bourbon","Kenneth", 10);
        Fad fad1 = new Fad(13,"Rødvin","Jonas", 25);
        Fad fad2 = new Fad(17,"Æble Juice","Adam", 50);

        //Act
        lager.addFad(fad,0);
        lager.addFad(fad1,1);
        lager.addFad(fad2,2);


        //Assert
        int forventet = 3;
        assertEquals(forventet, lager.getMaxPladser());
    }
}