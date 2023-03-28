package controller;

import model.Destillering;
import model.Fad;
import model.Lager;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Controller {

    public static Fad createFad(int fadNr, String fadType, String leverandoer, int fadStoerrelse) {
        Fad fad = new Fad(fadNr, fadType, leverandoer, fadStoerrelse);
        Storage.addFad(fad);
        return fad;
    }

    public static void updateFad(Fad fad, int fadNr, String fadType, String leverandoer, int fadStoerrelse) {
        fad.setFadNr(fadNr);
        fad.setFadType(fadType);
        fad.setLeverandoer(leverandoer);
        fad.setFadStoerrelse(fadStoerrelse);
    }

    public static ArrayList<Fad> soegningFad(ArrayList<Fad> fade, int nummer) {
        ArrayList<Fad> fads = new ArrayList<>();
        for (Fad f : fade) {
            if (f.getFadNr() == nummer) {
                fads.add(f);
            }
        }
        return fads;
    }

    public static Destillering createDestillering(LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, String medarbejder, double maengde, double alkoholProcent) {
        Destillering destillering = new Destillering(startDato, slutDato, maltBatch, kornsort, medarbejder, maengde, alkoholProcent);
        Storage.addDestillering(destillering);
        return destillering;
    }

    public static void updateDestillering(Destillering destillering, LocalDate startDato, LocalDate slutDato, String maltBatch, String kornsort, String medarbejder, double maengde, double alkoholProcent) {
        destillering.setStartDato(startDato);
        destillering.setSlutDato(slutDato);
        destillering.setMaltBatch(maltBatch);
        destillering.setKornsort(kornsort);
        destillering.setMedarbejder(medarbejder);
        destillering.setMaengde(maengde);
        destillering.setAlkoholProcent(alkoholProcent);
    }

    public static Lager createLager(int maxPladser, String lagernavn) {
        Lager lager = new Lager(maxPladser, lagernavn);
        Storage.addLager(lager);
        return lager;
    }

    public static void updateLager(Lager lager, int maxPladser, String lagernavn) {
        int max = 0;
        for (Map.Entry<Integer, Fad> entry : lager.getFade().entrySet()) {
            int key = entry.getKey();
            if (max < key) {
                max = key;
            }
        }
        if (maxPladser >= max) {
            lager.setMaxPladser(maxPladser);
            lager.setLagernavn(lagernavn);
        } else {
            throw new IllegalArgumentException("Der er et fad på en plads med et nummer over max");
        }

    }

    public static void addFadToLager(Fad fad, Lager lager, int plads) {
        for (Map.Entry<Integer, Fad> entry : lager.getFade().entrySet()) {
            Fad f = entry.getValue();
            if (f.getFadNr() == fad.getFadNr()) {
                throw new IllegalArgumentException("Der er allerede et fad med dette nummer på dette lager");
            }
        }
        lager.addFad(fad,plads);
    }

    public static void PaaFyldDestillatPaaFad(int maengde, LocalDate datoForPaaFyldning, Fad fad, Destillering destillering) {
        destillering.createPaafyldning(maengde, datoForPaaFyldning, fad);
    }

    public static void initContent() {
        Fad singleMalt = createFad(10,"Bourbon", "Kenneth", 10);
        Fad doubleMalt = createFad(13,"Rødvin", "Jonas", 25);
        Fad tripleMalt = createFad(17,"Æble juice","Adam", 50);
        Fad testingenlager = createFad(9, "Hvidvin", "Spanien", 15);

        Lager lager = createLager(3,"Olivers baghave");
        Lager lager1 = createLager(100,"Jakobs kælder");

        addFadToLager(tripleMalt,lager,2);
        addFadToLager(singleMalt,lager,3);
        addFadToLager(doubleMalt,lager1,1);

        Destillering destillering = createDestillering(LocalDate.of(2023,3,27),LocalDate.of(2023,3,31),"Single Malt","Byg","Snævar",500,67.2);
        destillering.createPaafyldning(10,LocalDate.now(),singleMalt);
        destillering.createPaafyldning(25,LocalDate.now(),doubleMalt);
    }
    public static ArrayList<Destillering> getDestilleringer() {
        return Storage.getDestilleringsListe();
    }

    public static ArrayList<Lager> getLager() {
        return Storage.getLagerListe();
    }

    public static ArrayList<Fad> getFad() {
        return Storage.getFadListe();
    }

    public static ArrayList<Fad> getIkkeFyldteFade() {
        ArrayList<Fad> result = new ArrayList<>();
        for (Fad f : Storage.getFadListe()) {
            if (f.tjekforPåfyldninger() != 0) {
                result.add(f);
            }
        }
        return result;
    }


}
