package model;

import java.time.LocalDate;
import java.util.Date;

public class Paafyldning {
    private double maengde;
    private LocalDate datoForPaafyldning;
    private Fad fad;
    private Destillering destillering;
    private Paafyldning paafyldning;

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

    public Paafyldning omhaeldTilFad(double maengde, LocalDate datoForPaafyldning, Fad fad) {
        paafyldning = new Paafyldning(maengde, datoForPaafyldning, fad, destillering);
        fad.addPaafyldning(paafyldning);
        return paafyldning;
    }

    public Paafyldning getPaafyldning() {
        return paafyldning;
    }

    @Override
    public String toString() {
        return "Påfyldning: " + destillering.getMaltBatch() + " " + datoForPaafyldning.toString() + " " + maengde + "L";
    }
}
