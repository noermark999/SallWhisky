package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        Paafyldning paafyldning = new Paafyldning(maengde, datoForPaafyldning, fad, destillering,true);
        paafyldning.paafyldninger.add(this);
        double x = (maengde)/this.fad.getPaafyldninger().size();
        for (Paafyldning p : this.fad.getPaafyldninger()) {
            p.setMaengde(p.getMaengde()-x);
        }
        this.fad.setMaengdeTilbage(this.fad.getMaengdeTilbage()-maengde);
    }

    public ArrayList<Fad> getTidligereFade() {
        ArrayList<Fad> result = new ArrayList<>();
        for (Paafyldning p : paafyldninger) {
            getTidligereFadeRec(result, p);
        }
        return result;
    }

    public void getTidligereFadeRec(List<Fad> list, Paafyldning paafyldning) {
        if (paafyldning.getPaafyldninger().isEmpty()) {
            if (!list.contains(paafyldning.getFad())) {
                list.add(paafyldning.getFad());
            }
            return;
        }
        for (Paafyldning p : paafyldning.getPaafyldninger()) {
            getTidligereFadeRec(list, p);
        }
        if (!list.contains(paafyldning.getFad())) {
            list.add(paafyldning.getFad());
        }
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
