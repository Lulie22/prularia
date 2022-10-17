package be.vdab.prularia.domain;

import java.time.LocalDate;

public class Bestelling {
    private final long bestelId;
    private final LocalDate besteldatum;
    private final long klantId;
    private final int betaals;
    private final String betalingscode;
    private final int betaalwijzeId;
    private final int annulatie;
    private final LocalDate annulatiedatum;
    private final String terugbetalingscode;
    private final long bestellingsStatusId;
    private final int actiecodeGebruikt;
    private final String bedrijfsnaam;
    private final String btwNummer;
    private final String voornaam;
    private final String familienaam;
    private final long facturatieAdresId;
    private final long leveringsAdresId;

    public Bestelling(long bestelId, LocalDate besteldatum, long klantId, int betaals, String betalingscode,
                      int betaalwijzeId, int annulatie, LocalDate annulatiedatum, String terugbetalingscode,
                      long bestellingsStatusId, int actiecodeGebruikt, String bedrijfsnaam, String btwNummer,
                      String voornaam, String familienaam, long facturatieAdresId, long leveringsAdresId) {
        this.bestelId = bestelId;
        this.besteldatum = besteldatum;
        this.klantId = klantId;
        this.betaals = betaals;
        this.betalingscode = betalingscode;
        this.betaalwijzeId = betaalwijzeId;
        this.annulatie = annulatie;
        this.annulatiedatum = annulatiedatum;
        this.terugbetalingscode = terugbetalingscode;
        this.bestellingsStatusId = bestellingsStatusId;
        this.actiecodeGebruikt = actiecodeGebruikt;
        this.bedrijfsnaam = bedrijfsnaam;
        this.btwNummer = btwNummer;
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.facturatieAdresId = facturatieAdresId;
        this.leveringsAdresId = leveringsAdresId;
    }

    public long getBestelId() {
        return bestelId;
    }

    public LocalDate getBesteldatum() {
        return besteldatum;
    }

    public long getKlantId() {
        return klantId;
    }

    public int getBetaals() {
        return betaals;
    }

    public String getBetalingscode() {
        return betalingscode;
    }

    public int getBetaalwijzeId() {
        return betaalwijzeId;
    }

    public int getAnnulatie() {
        return annulatie;
    }

    public LocalDate getAnnulatiedatum() {
        return annulatiedatum;
    }

    public String getTerugbetalingscode() {
        return terugbetalingscode;
    }

    public long getBestellingsStatusId() {
        return bestellingsStatusId;
    }

    public int getActiecodeGebruikt() {
        return actiecodeGebruikt;
    }

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public long getFacturatieAdresId() {
        return facturatieAdresId;
    }

    public long getLeveringsAdresId() {
        return leveringsAdresId;
    }
}
