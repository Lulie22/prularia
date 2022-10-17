package be.vdab.prularia.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class Artikel {
    @NotNull
    @PositiveOrZero
    private final long artikelId;
    @NotNull
    @NotBlank
    private final String ean;
    @NotNull
    @NotBlank
    private final String naam;
    @NotNull
    @NotBlank
    private final String beschrijving;
    @NotNull
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private final BigDecimal prijs;
    @PositiveOrZero
    private final int gewichtInGram;
    @PositiveOrZero
    private final int bestelpeil;
    @PositiveOrZero
    private final int voorraad;
    @PositiveOrZero
    private final int minimumVoorraad;
    @PositiveOrZero
    private final int maximumVoorraad;
    @PositiveOrZero
    private final int levertijd;
    @PositiveOrZero
    private final int aantalBesteldLeverancier;
    @PositiveOrZero
    private final int maxAantalInMagazijnPlaats;

    public Artikel(long artikelId, String ean, String naam, String beschrijving,
                   BigDecimal prijs, int gewichtInGram, int bestelpeil, int voorraad,
                   int minimumVoorraad, int maximumVoorraad, int levertijd,
                   int aantalBesteldLeverancier, int maxAantalInMagazijnPlaats) {
        this.artikelId = artikelId;
        this.ean = ean;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.gewichtInGram = gewichtInGram;
        this.bestelpeil = bestelpeil;
        this.voorraad = voorraad;
        this.minimumVoorraad = minimumVoorraad;
        this.maximumVoorraad = maximumVoorraad;
        this.levertijd = levertijd;
        this.aantalBesteldLeverancier = aantalBesteldLeverancier;
        this.maxAantalInMagazijnPlaats = maxAantalInMagazijnPlaats;
    }

    public long getArtikelId() {
        return artikelId;
    }

    public String getEan() {
        return ean;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getGewichtInGram() {
        return gewichtInGram;
    }

    public int getBestelpeil() {
        return bestelpeil;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getMinimumVoorraad() {
        return minimumVoorraad;
    }

    public int getMaximumVoorraad() {
        return maximumVoorraad;
    }

    public int getLevertijd() {
        return levertijd;
    }

    public int getAantalBesteldLeverancier() {
        return aantalBesteldLeverancier;
    }

    public int getMaxAantalInMagazijnPlaats() {
        return maxAantalInMagazijnPlaats;
    }
}
