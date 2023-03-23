package model;

import java.util.ArrayList;

public class Lager {
    private int maxPladser;
    private String lagernavn;
    private ArrayList<Fad> fade;

    public Lager(int maxPladser, String lagernavn) {
        this.maxPladser = maxPladser;
        this.lagernavn = lagernavn;
        this.fade = new ArrayList<>();
    }

    public void addFad(Fad fad, int plads) {
        if (fade.size() <= maxPladser) {
            if (!fade.contains(fad)) {
                fade.add(fad);
                fad.setLager(this, plads);
            } else {
                throw new IllegalArgumentException("Denne plads er allerede fyldt");
            }

        } else {
            throw new IllegalArgumentException("Lageret har ikke mere plads.");
        }
    }

    public void removeFad(Fad fad) {
        if (fade.contains(fad)) {
            fade.remove(fad);
            fad.setLager(null, 0);
        }
    }

    // getter og setter
    public int getMaxPladser() {
        return maxPladser;
    }

    public void setMaxPladser(int maxPladser) {
        this.maxPladser = maxPladser;
    }

    public String getLagernavn() {
        return lagernavn;
    }

    public void setLagernavn(String lagernavn) {
        this.lagernavn = lagernavn;
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    public void setFade(ArrayList<Fad> fade) {
        this.fade = fade;
    }

    @Override
    public String toString() {
        return lagernavn;
    }
}
