package ehu.isad;

import java.util.ArrayList;
import java.util.List;

public class Bilduma {

    private String izena;
    private List<Argazkia> lista;

    public Bilduma(String izena) {
        this.izena = izena;
    }

    @Override
    public String toString() {
        return izena;
    }

    public List<Argazkia> getLista() {
        return lista;
    }

    public String getIzena() {
        return izena;
    }
}
