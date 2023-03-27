package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Destillering {
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private String medarbejder;
    private double maengde;
    private double alkoholProcent;

    private ArrayList<Paafyldning> paafyldninger;

    public Destillering(LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, String medarbejder, double maengde, double alkoholProcent) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.medarbejder = medarbejder;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
        paafyldninger = new ArrayList<>();
    }

    public void createPaafyldning(double maengde, LocalDate datoForPaaFyldning, Fad fad) {
        if (maengde <= fad.getFadStoerrelse()) {
            if (maengde <= fad.tjekforPåfyldninger()) {
                Paafyldning paafyldning = new Paafyldning(maengde, datoForPaaFyldning, fad, this);
                paafyldninger.add(paafyldning);
            } else {
                throw new IllegalArgumentException("Der er for lidt plads i fadet");
            }
        } else {
            throw new IllegalArgumentException("Der er for lidt plads i fadet");
        }
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public String getKornsort() {
        return kornsort;
    }

    public String getMedarbejder() {
        return medarbejder;
    }

    public double getMaengde() {
        return maengde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public ArrayList<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        this.slutDato = slutDato;
    }

    public void setMaltBatch(String maltBatch) {
        this.maltBatch = maltBatch;
    }

    public void setKornsort(String kornsort) {
        this.kornsort = kornsort;
    }

    public void setMedarbejder(String medarbejder) {
        this.medarbejder = medarbejder;
    }

    public void setMaengde(double maengde) {
        this.maengde = maengde;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    @Override
    public String toString() {
        return startDato.toString() + " " + maltBatch;
    }
}
