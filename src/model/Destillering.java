package model;

import java.util.Date;

public class Destillering {
    private Date startDato;
    private Date slutDato;
    private String maltBatch;
    private String kornsort;
    private String medarbejder;
    private int maengde;
    private double alkoholProcent;

    public Destillering(Date startDato, Date slutDato, String maltBatch, String kornsort, String medarbejder, int maengde, double alkoholProcent) {
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.medarbejder = medarbejder;
        this.maengde = maengde;
        this.alkoholProcent = alkoholProcent;
    }

}
