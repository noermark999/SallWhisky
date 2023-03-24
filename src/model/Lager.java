package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Lager {
    private int maxPladser;
    private String lagernavn;
    private Map<Integer,Fad> fade;

    public Lager(int maxPladser, String lagernavn) {
        this.maxPladser = maxPladser;
        this.lagernavn = lagernavn;
        this.fade = new HashMap<>();
    }

    public void addFad(Fad fad, int plads) {
        if (fade.size() < maxPladser) {
            if (plads <= maxPladser) {
                if (!fade.containsKey(plads)) {
                    fade.put(plads, fad);
                    fad.setLager(this, plads);
                } else {
                    throw new IllegalArgumentException("Denne plads er allerede fyldt");
                }
            } else {
                throw new IllegalArgumentException("Denne plads er ugyldig da den er udenfor størrelsen af lageret");
            }
        } else {
            throw new IllegalArgumentException("Lageret har ikke mere plads.");
        }
    }

    public void removeFad(Fad fad) {
        if (fade.containsValue(fad)) {
            fade.remove(fad.getPlads());
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

    public Map<Integer, Fad> getFade() {
        return fade;
    }

    public void setFade(Map<Integer, Fad> fade) {
        this.fade = fade;
    }

    @Override
    public String toString() {
        return lagernavn;
    }
}
