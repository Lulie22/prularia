package be.vdab.prularia.domain;

public class InkomendeLeveringsLijn {
    private final long inkomendeLeveringsId;
    private final long artikelId;
    private final int aantalGoedgekeurd;
    private final int aantalTeruggestuurd;
    private final long magazijnplaatsId;

    public InkomendeLeveringsLijn(long inkomendeLeveringsId, long artikelId, int aantalGoedgekeurd,
                                  int aantalTeruggestuurd, long magazijnplaatsId) {
        this.inkomendeLeveringsId = inkomendeLeveringsId;
        this.artikelId = artikelId;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
        this.magazijnplaatsId = magazijnplaatsId;
    }

    public long getInkomendeLeveringsId() {
        return inkomendeLeveringsId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public int getAantalGoedgekeurd() {
        return aantalGoedgekeurd;
    }

    public int getAantalTeruggestuurd() {
        return aantalTeruggestuurd;
    }

    public long getMagazijnplaatsId() {
        return magazijnplaatsId;
    }
}
