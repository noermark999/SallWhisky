package model;

import java.time.LocalDate;
import java.util.Date;

public class Paafyldning {
    private double maengde;
    private LocalDate datoForPaafyldning;
    private Fad fad;
    private Destillering destillering;

    public Paafyldning(double maengde, LocalDate datoForPaafyldning, Fad fad, Destillering destillering) {
        this.maengde = maengde;
        this.datoForPaafyldning = datoForPaafyldning;
        this.fad = fad;
        this.destillering = destillering;
        fad.addPaafyldning(this);
    }

    public double getMaengde() {
        return maengde;
    }

    public LocalDate getDatoForPaafyldning() {
        return datoForPaafyldning;
    }

    public Fad getFad() {
        return fad;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
