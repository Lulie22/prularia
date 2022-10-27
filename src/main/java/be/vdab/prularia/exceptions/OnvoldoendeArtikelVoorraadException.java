package be.vdab.prularia.exceptions;

import be.vdab.prularia.domain.Artikel;

public class OnvoldoendeArtikelVoorraadException extends BestellingException {
    private static final long serialVersionUID = 1L;
    private long bestelId;
    private long bestellijnId;
    private long artikelId;



    public OnvoldoendeArtikelVoorraadException(long bestelId, long bestellijnId, long artikelId) {
        super(bestelId, bestellijnId);
        this.artikelId = artikelId;
    }
    public OnvoldoendeArtikelVoorraadException(long artikelId){
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
