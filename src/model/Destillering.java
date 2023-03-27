package model;


import java.util.ArrayList;
import java.util.Date;

public class Destillering {
    private Date startDato;
    private Date slutDato;
    private String maltBatch;
    private String kornsort;
    private String medarbejder;
    private int maengde;
    private double alkoholProcent;
    //

    private ArrayList<Paafyldning> paafyldninger;

    public Destillering(Date startDato, Date slutDato, String maltBatch, String kornsort, String medarbejder, int maengde, double alkoholProcent) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.medarbejder = medarbejder;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
        paafyldninger = new ArrayList<>();
    }

    public void paaFyldDestillatPaaFad(int maengde, Date datoForPaaFyldning, Fad fad) {
        Paafyldning paafyldning = new Paafyldning(maengde,datoForPaaFyldning,fad);
        paafyldninger.add(paafyldning);
    }

    public Date getStartDato() {
        return startDato;
    }

    public Date getSlutDato() {
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
}
