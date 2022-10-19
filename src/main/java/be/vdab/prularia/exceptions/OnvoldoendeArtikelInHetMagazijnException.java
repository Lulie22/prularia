package be.vdab.prularia.exceptions;

public class OnvoldoendeArtikelInHetMagazijnException extends RuntimeException{
    private final long serialVersionUID = 1L;
    private long bestelId;
    private long bestellijnId;
    private long artikelId;

    public OnvoldoendeArtikelInHetMagazijnException(long bestelId, long bestellijnId, long artikelId) {
        this.bestelId = bestelId;
        this.bestellijnId = bestellijnId;
        this.artikelId = artikelId;
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
}
