package be.vdab.prularia.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class GeleverdArtikel {
    @NotNull
    @PositiveOrZero
    private long artikelId;
    @NotNull
    @NotBlank
    private final String ean;
    @NotNull
    @Positive
    private final int aantal;
    @NotNull
    @PositiveOrZero
    private final long aantalGoedgekeurd;
    @NotNull
    @PositiveOrZero
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
