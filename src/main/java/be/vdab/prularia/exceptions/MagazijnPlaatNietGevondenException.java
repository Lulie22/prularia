package be.vdab.prularia.exceptions;

public class MagazijnPlaatNietGevondenException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final long magazijnPlaatsId;

    public MagazijnPlaatNietGevondenException(long magazijnPlaatsId) {
        this.magazijnPlaatsId = magazijnPlaatsId;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }
}
