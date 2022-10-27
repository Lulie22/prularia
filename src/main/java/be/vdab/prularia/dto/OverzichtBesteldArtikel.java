package be.vdab.prularia.dto;

public record OverzichtBesteldArtikel(long artikelId,long magazijnPlaatsId, String naam, char rij, int rek, int aantal, boolean aangevinkt ) {
}
