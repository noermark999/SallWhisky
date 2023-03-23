package controller;

import model.Fad;
import model.Lager;
import storage.Storage;

import java.util.ArrayList;

public class Controller {

    public static Fad createFad(int fadNr, String fadType, String leverandoer) {
        Fad fad = new Fad(fadNr, fadType, leverandoer);
        Storage.addFad(fad);
        return fad;
    }

    public static Lager createLager(int maxPladser, String lagernavn) {
        Lager lager = new Lager(maxPladser, lagernavn);
        Storage.addLager(lager);
        return lager;
    }

    public static void initContent() {
        Fad singleMalt = createFad(10,"Bourbon", "Kenneth");
        Fad doubleMalt = createFad(13,"Rødvin", "Jonas");
        Fad tripleMalt = createFad(17,"Æble juice","Adam");

        Lager lager = createLager(50,"Olivers baghave");
        Lager lager1 = createLager(100,"Jakobs kælder");

        lager.addFad(tripleMalt, 2);
        singleMalt.setLager(lager, 3);
        doubleMalt.setLager(lager1, 1);
    }

    public static ArrayList<Lager> getLager() {
        return Storage.getLagerListe();
    }

    public static ArrayList<Fad> getFad() {
        return Storage.getFadListe();
    }


}
