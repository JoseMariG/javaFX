package ehu.isad;

public class Argazkia {
    String izena;
    String path;

    public Argazkia(String izena, String path) {
        this.izena = izena;
        this.path = path;
    }

    @Override
    public String toString() {
        return izena;
    }

    public String getIzena() {
        return izena;
    }

    public String getFitx() {
        return path;
    }
}
