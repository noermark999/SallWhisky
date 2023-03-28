package model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class PaafyldningsTest {

    @Test
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
