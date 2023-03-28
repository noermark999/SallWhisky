package model;

import java.util.Date;
import java.util.HashSet;

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
    private HashSet<Paafyldning> paafyldninger;

    public Fad(int fadNr, String fadType, String leverandoer, int fadStoerrelse) {
        this.fadNr = fadNr;
        this.fadType = fadType;
        this.leverandoer = leverandoer;
        this.fadStoerrelse = fadStoerrelse;
        paafyldninger = new HashSet<>();
    }
    public void setLager(Lager lager, int plads) {
        if (this.lager != lager || this.plads != plads) {
            Lager oldLager = this.lager;
            if (oldLager != null) {
                oldLager.removeFad(this);
            }
            this.lager = lager;
            this.plads = plads;
            if (lager != null && !lager.getFade().containsValue(this)) {
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

    public double tjekforPåfyldninger() {
        double result = 0;

        if (paafyldninger.size() == 0) {
            return fadStoerrelse;
        } else {
            for (Paafyldning paafyldning : paafyldninger) {
                result += paafyldning.getMaengde();

            }
            return fadStoerrelse - result;
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

    public void setFadNr(int fadNr) {
        this.fadNr = fadNr;
    }

    public void setFadType(String fadType) {
        this.fadType = fadType;
    }

    public void setFadStoerrelse(int fadStoerrelse) {
        this.fadStoerrelse = fadStoerrelse;
    }

    public void setPlads(int plads) {
        this.plads = plads;
    }

    public void setLeverandoer(String leverandoer) {
        this.leverandoer = leverandoer;
    }

    public void addPaafyldning(Paafyldning p) {
        paafyldninger.add(p);
    }

    public HashSet<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }

    @Override
    public String toString() {
        if (paafyldninger.isEmpty()) {
            return "Fad nummer : " + fadNr + " plads : " + plads;
        }
        double fyldt = 0;
        for (Paafyldning p : paafyldninger) {
            fyldt += p.getMaengde();
        }
        return "Fad nummer : " + fadNr + " plads : " + plads + " Fyldt: " + fyldt + "/" + fadStoerrelse + "L";
    }
}
