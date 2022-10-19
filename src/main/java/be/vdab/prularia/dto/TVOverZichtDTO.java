package be.vdab.prularia.dto;

public class TVOverZichtDTO {
    private final long bestelId;
    private final int aantal;
    private final double totaalGewicht;

    public TVOverZichtDTO(long bestelId, int aantal, double totaalGewicht) {
        this.bestelId = bestelId;
        this.aantal = aantal;
        this.totaalGewicht = totaalGewicht;
    }

    public long getBestelId() {
        return bestelId;
    }

    public int getAantal() {
        return aantal;
    }

    public double getTotaalGewicht() {
        return totaalGewicht;
    }
}
