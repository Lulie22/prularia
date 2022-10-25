package be.vdab.prularia.dto;

public class GeleverdArtikel {
    private long artikelId;
    private final String ean;
    private final int aantal;
    private final long aantalGoedgekeurd;
    private final int aantalTeruggestuurd;

    public GeleverdArtikel(long artikelId, String ean, int aantal, long aantalGoedgekeurd, int aantalTeruggestuurd) {
        this.artikelId = artikelId;
        this.ean = ean;
        this.aantal = aantal;
        this.aantalGoedgekeurd = aantalGoedgekeurd;
        this.aantalTeruggestuurd = aantalTeruggestuurd;
    }

    public void setArtikelId(long artikelId) {
        this.artikelId = artikelId;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getEan() {
        return ean;
    }

    public int getAantal() {
        return aantal;
    }

    public long getAantalGoedgekeurd() {
        return aantalGoedgekeurd;
    }

    public int getAantalTeruggestuurd() {
        return aantalTeruggestuurd;
    }
}
