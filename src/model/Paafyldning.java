package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        fad.addPaafyldningOriginal(this);
        paafyldninger = new ArrayList<>();
    }

    private Paafyldning(double maengde, LocalDate datoForPaafyldning, Fad fad, Destillering destillering, boolean omhaeldning) {
        this.maengde = maengde;
        this.datoForPaafyldning = datoForPaafyldning;
        this.fad = fad;
        this.destillering = destillering;
        fad.addPaaFyldningOmHaeldning(this);
        paafyldninger = new ArrayList<>();
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
        if (maengde <= this.fad.getMaengdeTilbage()) {
            if (maengde <= fad.getFadStoerrelse()) {
                Paafyldning paafyldning = new Paafyldning(maengde, datoForPaafyldning, fad, destillering, true);
                paafyldning.paafyldninger.add(this);
                fad.setOriginalPaafyldningsDato(fad.erDatoSenere(this.fad.getOriginalPaafyldningsDato()));
                double x = (maengde) / this.fad.getPaafyldninger().size();
                for (Paafyldning p : this.fad.getPaafyldninger()) {
                    p.setMaengde(p.getMaengde() - x);
                }
                this.fad.setMaengdeTilbage(this.fad.getMaengdeTilbage() - maengde);
            } else {
                throw new IllegalArgumentException("Den angivende mængde er mere end hvad der er plads til i det nye fad");
            }
        } else {
            throw new IllegalArgumentException("Den angivende mængde er mere end hvad der er i fadet");
        }
    }

    public HashSet<Fad> getTidligereFade() {
        HashSet<Fad> result = new HashSet<>();
        for (Paafyldning p : paafyldninger) {
            getTidligereFadeRec(result, p);
        }
        return result;
    }

    private void getTidligereFadeRec(Set<Fad> set, Paafyldning paafyldning) {
        for (Paafyldning p : paafyldning.getPaafyldninger()) {
            getTidligereFadeRec(set, p);
        }
        set.add(paafyldning.getFad());
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
