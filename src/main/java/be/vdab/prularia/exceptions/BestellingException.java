package be.vdab.prularia.exceptions;

public class BestellingException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private long bestelId;
    private long bestellijnId;

    public BestellingException() {
    }
    public BestellingException(long bestelId) {
        this.bestelId = bestelId;
    }
    public BestellingException(long bestelId, long bestellijnId) {
        this.bestelId = bestelId;
        this.bestellijnId = bestellijnId;
    }
}
