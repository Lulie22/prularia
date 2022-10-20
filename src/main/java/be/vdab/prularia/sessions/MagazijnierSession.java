package be.vdab.prularia.sessions;

import be.vdab.prularia.dto.OverzichtBesteldArtikel;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

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
}
