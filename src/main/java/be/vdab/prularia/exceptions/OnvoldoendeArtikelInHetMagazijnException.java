package be.vdab.prularia.exceptions;

public class OnvoldoendeArtikelInHetMagazijnException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private long bestelId;
    private long bestellijnId;
    private long artikelId;
    private long magazijnPlaatsId;

    public OnvoldoendeArtikelInHetMagazijnException(long bestelId, long bestellijnId, long artikelId, long magazijnPlaatsId) {
        this.bestelId = bestelId;
        this.bestellijnId = bestellijnId;
        this.artikelId = artikelId;
        this.magazijnPlaatsId = magazijnPlaatsId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public long getBestellijnId() {
        return bestellijnId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }
}
