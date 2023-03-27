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
    private int maengde;
    private double alkoholProcent;
    //

    private ArrayList<Paafyldning> paafyldninger;

    public Destillering(LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, String medarbejder, int maengde, double alkoholProcent) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.medarbejder = medarbejder;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
        paafyldninger = new ArrayList<>();
    }

    public void createPaafyldning(int maengde, LocalDate datoForPaaFyldning, Fad fad) {
        Paafyldning paafyldning = new Paafyldning(maengde,datoForPaaFyldning,fad,this);
        paafyldninger.add(paafyldning);
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

    public int getMaengde() {
        return maengde;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public ArrayList<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }
}
