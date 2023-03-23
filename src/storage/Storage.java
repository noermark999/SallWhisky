package storage;

import model.Fad;
import model.Lager;

import java.util.ArrayList;

public class Storage {

    private static ArrayList<Fad> fadListe = new ArrayList<>();

    private static ArrayList<Lager> lagerListe = new ArrayList<>();

    public static void addFad(Fad fad) {
        fadListe.add(fad);
    }

    public static void addLager(Lager lager) {
        lagerListe.add(lager);
    }

    public static ArrayList<Fad> getFadListe() {
        return fadListe;
    }

    public static ArrayList<Lager> getLagerListe() {
        return lagerListe;
    }
}
