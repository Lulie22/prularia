package be.vdab.prularia.exceptions;

public class MagazijnPlaatsNietGevondenException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final long magazijnPlaatsId;

    public MagazijnPlaatsNietGevondenException(long magazijnPlaatsId) {
        this.magazijnPlaatsId = magazijnPlaatsId;
    }

    public long getMagazijnPlaatsId() {
        return magazijnPlaatsId;
    }
}
