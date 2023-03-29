package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flaske {
    private String navn;
    private static int flaskeNr = 0;
    private LocalDate datoForTapning;
    private double alkoholProcent;
    private double flaskestoerrelse;
    private double fortyndingsmaengde;
    private String vandType;
    private String beskrivelse;
    private HashMap<Fad, Double> fade;

    public Flaske(String navn, LocalDate datoForTapning, double alkoholProcent, double flaskestoerrelse, double fortyndingsmaengde, String vandType, String beskrivelse, double whiskeyMaengde, Fad fad) {
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
        if (maengde + samletMaengde() + fortyndingsmaengde > flaskestoerrelse) {
            throw new IllegalArgumentException("Kan ikke tilføje fad da flasken ikke har plads til den angivende mængde");
        } else {
            fade.put(fad, maengde);
            fad.addFlaske(this, maengde);
        }
    }

    public double samletMaengde() {
        double result = 0;
        for (Map.Entry<Fad, Double> e : fade.entrySet()) {
            result += e.getValue();
        }
        return result;
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

    public double getFlaskestoerrelse() {
        return flaskestoerrelse;
    }

    public void setFlaskestoerrelse(double flaskestoerrelse) {
        this.flaskestoerrelse = flaskestoerrelse;
    }

    public double getFortyndingsmaengde() {
        return fortyndingsmaengde;
    }

    public void setFortyndingsmaengde(double fortyndingsmaengde) {
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
