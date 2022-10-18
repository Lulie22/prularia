package be.vdab.prularia.DTO;

public class TVOverZichtDTO {
    private final long bestellingNummer;
    private final int aantal;
    private final double totaalGewicht;

    public TVOverZichtDTO(long bestellingNummer, int aantal, double totaalGewicht) {
        this.bestellingNummer = bestellingNummer;
        this.aantal = aantal;
        this.totaalGewicht = totaalGewicht;
    }

    public long getBestellingNummer() {
        return bestellingNummer;
    }

    public int getAantal() {
        return aantal;
    }

    public double getTotaalGewicht() {
        return totaalGewicht;
    }
}
