package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Flaske {
    private String navn;
    private static int flaskeNr = 0;
    private LocalDate datoForTapning;
    private double alkoholProcent;
    private int flaskestoerrelse;
    private int fortyndingsmaengde;
    private String vandType;
    private String beskrivelse;
    private HashMap<Fad, Double> fade;

    public Flaske(String navn, LocalDate datoForTapning, double alkoholProcent, int flaskestoerrelse, int fortyndingsmaengde, String vandType, String beskrivelse, double whiskeyMaengde, Fad fad) {
        this.navn = navn;
        flaskeNr++;
        this.datoForTapning = datoForTapning;
        this.alkoholProcent = alkoholProcent;
        this.flaskestoerrelse = flaskestoerrelse;
        this.fortyndingsmaengde = fortyndingsmaengde;
        this.vandType = vandType;
        this.beskrivelse = beskrivelse;
        fade = new HashMap<>();
        fade.put(fad,whiskeyMaengde);
    }

    public void addFad(double maengde, Fad fad) {
        fade.put(fad,maengde);
        fad.addFlaske(this, maengde);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getFlaskeNr() {
        return flaskeNr;
    }

    public LocalDate getDatoForTapning() {
        return datoForTapning;
    }

    public void setDatoForTapning(LocalDate datoForTapning) {
        this.datoForTapning = datoForTapning;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public int getFlaskestoerrelse() {
        return flaskestoerrelse;
    }

    public void setFlaskestoerrelse(int flaskestoerrelse) {
        this.flaskestoerrelse = flaskestoerrelse;
    }

    public int getFortyndingsmaengde() {
        return fortyndingsmaengde;
    }

    public void setFortyndingsmaengde(int fortyndingsmaengde) {
        this.fortyndingsmaengde = fortyndingsmaengde;
    }

    public String getVandType() {
        return vandType;
    }

    public void setVandType(String vandType) {
        this.vandType = vandType;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public HashMap<Fad, Double> getFade() {
        return fade;
    }
}
