package be.vdab.prularia.domain;

public class Bestellijn {
    private final long bestellijnId;
    private final long bestelId;
    private final long artikelId;
    private final int aantalBestelId;
    private final int aantalGeannuleerd;

    public Bestellijn(long bestellijnId, long bestelId, long artikelId, int aantalBestelId, int aantalGeannuleerd) {
        this.bestellijnId = bestellijnId;
        this.bestelId = bestelId;
        this.artikelId = artikelId;
        this.aantalBestelId = aantalBestelId;
        this.aantalGeannuleerd = aantalGeannuleerd;
    }

    public long getBestellijnId() {
        return bestellijnId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public int getAantalBestelId() {
        return aantalBestelId;
    }

    public int getAantalGeannuleerd() {
        return aantalGeannuleerd;
    }
}
