package model;

import java.util.Date;

public class Paafyldning {
    private int maengde;
    private Date datoForPaafyldning;
    private Fad fad;
    private Destillering destillering

    public Paafyldning(int maengde, Date datoForPaafyldning, Fad fad, Destillering destillering) {
        this.maengde = maengde;
        this.datoForPaafyldning = datoForPaafyldning;
        this.fad = fad;
        this.destillering = destillering;
        fad.addPaafyldning(this);
    }

    public int getMaengde() {
        return maengde;
    }

    public Date getDatoForPaafyldning() {
        return datoForPaafyldning;
    }

    public Fad getFad() {
        return fad;
    }

    public Destillering getDestillering() {
        return destillering;
    }
}
