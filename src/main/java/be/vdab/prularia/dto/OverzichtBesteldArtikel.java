package be.vdab.prularia.dto;

public class OverzichtBesteldArtikel {
    private final long artikelId;
    private final String naam;
    private final char rij;
    private final int rek;
    private final int aantal;
    private boolean aangevinkt;
    private final long magazijnplaatsId;

    public OverzichtBesteldArtikel(long artikelId, String naam, char rij, int rek, int aantal, boolean aangevinkt, long magazijnplaatsId) {
        this.artikelId = artikelId;
        this.naam = naam;
        this.rij = rij;
        this.rek = rek;
        this.aantal = aantal;
        this.aangevinkt = aangevinkt;
        this.magazijnplaatsId = magazijnplaatsId;
    }

    public void setAangevinkt(boolean aangevinkt) {
        this.aangevinkt = aangevinkt;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getNaam() {
        return naam;
    }

    public char getRij() {
        return rij;
    }

    public int getRek() {
        return rek;
    }

    public int getAantal() {
        return aantal;
    }

    public boolean isAangevinkt() {
        return aangevinkt;
    }

    public long getMagazijnplaatsId() {
        return magazijnplaatsId;
    }
}
