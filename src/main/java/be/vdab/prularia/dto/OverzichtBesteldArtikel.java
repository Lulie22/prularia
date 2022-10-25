package be.vdab.prularia.dto;

public record OverzichtBesteldArtikel(long artikelId, String naam, char rij, int rek, int aantal, boolean aangevinkt, long magazijnplaatsId ) {
}
