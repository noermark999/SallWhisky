package model;

import java.util.Date;

public class Paafyldning {
    private int maengde;
    private Date datoForPaafyldning;
    private Fad fad;

    public Paafyldning(int maengde, Date datoForPaafyldning, Fad fad) {
        this.maengde = maengde;
        this.datoForPaafyldning = datoForPaafyldning;
        this.fad = fad;
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
}
