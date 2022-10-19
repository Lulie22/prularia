package be.vdab.prularia.domain;

public class MagazijnPlaats {
    private final long magazijnPlaatsId;
    private final long artikelId;
    private final String rij;
    private final int rek;
    private final int aantal;

    public MagazijnPlaats(long magazijnPlaatsId, long artikelId, String rij, int rek, int aantal) {
        this.magazijnPlaatsId = magazijnPlaatsId;
        this.artikelId = artikelId;
        this.rij = rij;
        this.rek = rek;
        this.aantal = aantal;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getRij() {
        return rij;
    }

    public int getRek() {
        return rek;
    }

    public int getAantal() {
        return aantal;
    }
}
