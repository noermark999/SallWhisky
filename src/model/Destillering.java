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
        if (fad.getFadStoerrelse() <= maengde) {
            if (fad.tjekforPåfyldninger() <= maengde) {
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
}
