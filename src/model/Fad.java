package model;

import java.util.Date;

public class Fad {
    private Date datoForPaafyldning;
    private int fadNr;
    private String fadType;
    private int fadStoerrelse;
    private String spiritBatchNr;
    private double alkoholProcent;
    private String medarbejder;
    private int plads;
    private String leverandoer;
    private Lager lager;

    public Fad(int fadNr, String fadType, String leverandoer, int fadStoerrelse) {
        this.fadNr = fadNr;
        this.fadType = fadType;
        this.leverandoer = leverandoer;
        this.fadStoerrelse = fadStoerrelse;
    }
    public void setLager(Lager lager, int plads) {
        if (this.lager != lager) {
            Lager oldLager = this.lager;
            if (oldLager != null) {
                oldLager.removeFad(this);
            }
            this.lager = lager;
            this.plads = plads;
            if (lager != null) {
                lager.addFad(this, plads);
            }
        }
    }

    public void flytPlads(int plads) {
        if (this.lager != null) {
            lager.removeFad(this);
            lager.addFad(this,plads);
        } else {
            throw new IllegalArgumentException("Dette fad er ikke lagt på lager");
        }
    }

    public Date getDatoForPaafyldning() {
        return datoForPaafyldning;
    }

    public int getFadNr() {
        return fadNr;
    }

    public String getFadType() {
        return fadType;
    }

    public int getFadStoerrelse() {
        return fadStoerrelse;
    }

    public String getSpiritBatchNr() {
        return spiritBatchNr;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public String getMedarbejder() {
        return medarbejder;
    }

    public int getPlads() {
        return plads;
    }

    public String getLeverandoer() {
        return leverandoer;
    }

    public Lager getLager() {
        return lager;
    }
    @Override
    public String toString() {
        return "Fad nummer : " + fadNr + " plads : " + plads;
    }
}
