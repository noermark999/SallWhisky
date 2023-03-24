package controller;

import model.Fad;
import model.Lager;
import storage.Storage;

import java.util.ArrayList;

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

    public static Lager createLager(int maxPladser, String lagernavn) {
        Lager lager = new Lager(maxPladser, lagernavn);
        Storage.addLager(lager);
        return lager;
    }

    public static void addFadToLager(Fad fad, Lager lager, int plads) {
        //fad.setLager(lager,plads);
        lager.addFad(fad,plads);
    }

    public static void initContent() {
        Fad singleMalt = createFad(10,"Bourbon", "Kenneth", 10);
        Fad doubleMalt = createFad(13,"Rødvin", "Jonas", 25);
        Fad tripleMalt = createFad(17,"Æble juice","Adam", 50);
        Fad testingenlager = createFad(9, "Hvidvin", "Spanien", 15);

        Lager lager = createLager(50,"Olivers baghave");
        Lager lager1 = createLager(100,"Jakobs kælder");

        addFadToLager(tripleMalt,lager,2);
        addFadToLager(singleMalt,lager,3);
        addFadToLager(doubleMalt,lager1,1);
    }

    public static ArrayList<Lager> getLager() {
        return Storage.getLagerListe();
    }

    public static ArrayList<Fad> getFad() {
        return Storage.getFadListe();
    }


}
