package be.vdab.prularia.sessions;

import be.vdab.prularia.dto.OverzichtBesteldArtikel;
import be.vdab.prularia.exceptions.OverzichtBesteldArtikelNietGevondenException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Component
@SessionScope
public class MagazijnierSession {
    private long bestelId;
    private List<OverzichtBesteldArtikel> lijstVanBesteldeArtikels;

    public long getBestelId() {
        return bestelId;
    }

    public void setBestelId(long bestelId) {
        this.bestelId = bestelId;
    }

    public List<OverzichtBesteldArtikel> getLijstVanBesteldeArtikels() {
        return lijstVanBesteldeArtikels;
    }

    public void setLijstVanBesteldeArtikels(List<OverzichtBesteldArtikel> lijstVanBesteldeArtikels) {
        this.lijstVanBesteldeArtikels = lijstVanBesteldeArtikels;
    }

    public boolean setStatusBesteldArtikel(long magazijnplaatsId, boolean status) {
        boolean aangepast = false;
        for (var bestellijn : lijstVanBesteldeArtikels) {
            if (bestellijn.magazijnplaatsId() == magazijnplaatsId) {
                aangepast = true;
                break;
            }
        }
        return aangepast;
    }

    public boolean besteldeArtikelsZijnOpgehaald() {
        return lijstVanBesteldeArtikels.stream()
                .allMatch(OverzichtBesteldArtikel::aangevinkt);
    }
}
