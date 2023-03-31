package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Paafyldning {
    private double maengde;
    private LocalDate datoForPaafyldning;
    private Fad fad;
    private Destillering destillering;
    private ArrayList<Paafyldning> paafyldninger;

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

    public void omhaeldTilFad(double maengde, LocalDate datoForPaafyldning, Fad fad) {
        paafyldninger.add(new Paafyldning(maengde, datoForPaafyldning, fad, destillering));
        for (Paafyldning p : fad.getPaafyldninger()) {
            p.setMaengde(p.getMaengde()-maengde);
        }
    }

    public ArrayList<Fad> getTidligereFade() {
        ArrayList<Fad> result = new ArrayList<>();

        return result;
    }

    public ArrayList<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    @Override
    public String toString() {
        return "Påfyldning: " + destillering.getMaltBatch() + " " + datoForPaafyldning.toString() + " " + maengde + "L";
    }
}
