package be.vdab.prularia.exceptions;

public class ArtikelNietGevondenException extends BestellingException{
    private static final long serialVersionUID = 1L;
    private long artikelId;
    private long bestelId;
    private long bestellijnId;

    public ArtikelNietGevondenException(long bestelId,long bestellijnId,long artikelId) {
        super(bestelId,bestellijnId);
        this.artikelId = artikelId;
    }
    public ArtikelNietGevondenException(long artikelId) {
        this.artikelId = artikelId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public long getBestellijnId() {
        return bestellijnId;
    }
}
