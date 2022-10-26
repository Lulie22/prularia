package be.vdab.prularia.services;

import be.vdab.prularia.domain.Bestellijn;
import be.vdab.prularia.domain.MagazijnPlaats;
import be.vdab.prularia.domain.UitgaandeLevering;
import be.vdab.prularia.dto.OverzichtBesteldArtikel;
import be.vdab.prularia.dto.TVOverZichtDTO;
import be.vdab.prularia.exceptions.GeenVolgendeBestellingException;
import be.vdab.prularia.exceptions.OnvoldoendeArtikelInHetMagazijnException;
import be.vdab.prularia.exceptions.UitgaandaLeveringsStatusNietGevondenException;
import be.vdab.prularia.repositories.*;
import be.vdab.prularia.sessions.MagazijnierSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BestellingService {
    private final BestellingRepository bestellingRepository;
    private final BestellijnRepository bestellijnRepository;
    private final MagazijnPlaatsRepository magazijnPlaatsRepository;
    private final ArtikelRepository artikelRepository;
    private final UitgaandeLeveringRepository uitgaandeLeveringRepository;
    private final UitgaandaLeveringsStatusRepository uitgaandaLeveringsStatusRepository;
    private final MagazijnierSession magazijnierSession;

    public BestellingService(BestellingRepository bestellingRepository, BestellijnRepository bestellijnRepository,
                             MagazijnPlaatsRepository magazijnPlaatsRepository, ArtikelRepository artikelRepository,
                             UitgaandeLeveringRepository uitgaandeLeveringRepository,
                             UitgaandaLeveringsStatusRepository uitgaandaLeveringsStatusRepository,
                             MagazijnierSession magazijnierSession) {
        this.bestellingRepository = bestellingRepository;
        this.bestellijnRepository = bestellijnRepository;
        this.magazijnPlaatsRepository = magazijnPlaatsRepository;
        this.artikelRepository = artikelRepository;
        this.uitgaandeLeveringRepository = uitgaandeLeveringRepository;
        this.uitgaandaLeveringsStatusRepository = uitgaandaLeveringsStatusRepository;
        this.magazijnierSession = magazijnierSession;
    }

    public List<TVOverZichtDTO> findEersteVijfBestellingen() {
        return bestellingRepository.findEersteVijfBestellingen();
    }

    public int aantalBestellingen() {
        return bestellingRepository.aantalBestellingen();
    }

    public void vindVolgendeBestelling() {
        Optional<Long> optionalBestelId = bestellingRepository.vindIdVolgendeBestelling();
        if (optionalBestelId.isEmpty()) {
            throw new GeenVolgendeBestellingException();
        } else {
            long bestelId = optionalBestelId.get();
            List<Bestellijn> bestellijnen = bestellijnRepository.vindBestellijnenByBestelId(bestelId);
            HashMap<Long, Integer> magazijnplaatsIdEnAantal = new HashMap<>();
            for (Bestellijn bestellijn : bestellijnen) {
                // Zoek magazijnplaatsen via artikelId
                List<MagazijnPlaats> magazijnplaatsen = magazijnPlaatsRepository
                        .vindMagazijnPlaatsenByArtikelId(bestellijn.getArtikelId());
                // Bereken het benodigd aantal voor dit artikel
                int aantalNodig = bestellijn.getAantalBesteld() - bestellijn.getAantalGeannuleerd();
                // Nodige variables
                int i = 0;
                // Bereken voor elke artikelId de magazijnplaatsen en sla op in de hashmap
                try {
                    while (aantalNodig > 0) {
                        // voldoende stock in magazijnplaats
                        if (magazijnplaatsen.get(i).getAantal() >= aantalNodig) {
                            magazijnplaatsIdEnAantal.put(
                                    magazijnplaatsen.get(i).getMagazijnPlaatsId(),
                                    aantalNodig);
                            aantalNodig = 0;
                            // onvoldoende stock in magazijnplaats
                        } else {
                            magazijnplaatsIdEnAantal.put(
                                    magazijnplaatsen.get(i).getMagazijnPlaatsId(),
                                    magazijnplaatsen.get(i).getAantal());
                            aantalNodig -= magazijnplaatsen.get(i).getAantal();
                        }
                        i++;
                    }
                } catch (IndexOutOfBoundsException ex) {
                    throw new OnvoldoendeArtikelInHetMagazijnException(
                            bestelId,
                            bestellijn.getBestellijnId(),
                            bestellijn.getArtikelId());
                }
            }
            // Maak een lijst van OverzichtBesteldArtikel door middel van de HashMap
            List<OverzichtBesteldArtikel> lijstVanEerstvolgendeBestelling =
                    magazijnPlaatsRepository.vindBesteldeArtikels(magazijnplaatsIdEnAantal);
            // indien meerdere magazijniers: hier de database en stock al aanpassen.

            // Toevoegen van bestelId en lijstVanEerstVolgendeBestelling toevoegen aan de session
            magazijnierSession.setBestelId(bestelId);
            magazijnierSession.setLijstVanBesteldeArtikels(lijstVanEerstvolgendeBestelling);
        }
    }

    @Transactional
    public void afgewerkteBestelling() {
        var bestelId = magazijnierSession.getBestelId();
        var besteldArtikelLijst = magazijnierSession.getLijstVanBesteldeArtikels();

        var besteling = bestellingRepository.vindBestellingById(bestelId).orElseThrow(() ->
                {
                    throw new GeenVolgendeBestellingException();
                }
        );
        var uitgaandeLeveringStatusId = uitgaandaLeveringsStatusRepository.vindUitgaandeLeveringStatusId().orElseThrow(()->{
            throw new UitgaandaLeveringsStatusNietGevondenException();});
        besteldArtikelLijst.stream().forEach(bestellijn -> {
            magazijnPlaatsRepository.verlaagAantalArtikelInMagazijn(bestellijn.artikelId(), bestellijn.aantal());
            artikelRepository.verlaagArtikelVoorraad(bestellijn.artikelId(), bestellijn.aantal());
        });

        uitgaandeLeveringRepository.create(new UitgaandeLevering(
                0, bestelId, LocalDate.now(), LocalDate.now().plusDays(1), "",
                besteling.getKlantId(), uitgaandeLeveringStatusId));
    }
}
