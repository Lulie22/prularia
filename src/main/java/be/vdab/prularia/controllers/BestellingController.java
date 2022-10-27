package be.vdab.prularia.controllers;

import be.vdab.prularia.dto.MagazijnplaatsIdEnStatus;
import be.vdab.prularia.exceptions.GeenVolgendeBestellingException;
import be.vdab.prularia.exceptions.OnvoldoendeArtikelInHetMagazijnException;
import be.vdab.prularia.exceptions.OverzichtBesteldArtikelNietGevondenException;
import be.vdab.prularia.services.BestellingService;
import be.vdab.prularia.sessions.MagazijnierSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("bestelling")
public class BestellingController {
    private final BestellingService bestellingService;
    private final MagazijnierSession magazijnierSession;

    public BestellingController(BestellingService bestellingService, MagazijnierSession magazijnierSession) {
        this.bestellingService = bestellingService;
        this.magazijnierSession = magazijnierSession;
    }

    // voor een nieuwe bestelling op te vragen
    @GetMapping("nieuw")
    public ModelAndView vindVolgendeBestelling() {
        // Check of er een volgende bestelling is adhv bestelId
            // indien er geen is, vraag en check de nieuwe bestelling
        if (magazijnierSession.getLijstVanBesteldeArtikels() == null) {
            try {
                bestellingService.vindVolgendeBestelling();
            } catch (GeenVolgendeBestellingException ex) {
                return new ModelAndView("bestelbon")
                        .addObject("geenVolgendeBestelling", true);
            } catch (OnvoldoendeArtikelInHetMagazijnException ex) {
                return new ModelAndView("bestelbon")
                        .addObject("onvoldoendeArtikelen", true)
                        .addObject("onvoldoendeArtikelenBestelId", ex.getBestelId())
                        .addObject("onvoldoendeArtikelenBestellijnId", ex.getBestellijnId())
                        .addObject("onvoldoendeArtikelenArtikelId", ex.getArtikelId());
            }
            // heeft op dit punt een volgende bestelling gevonden en opgeslagen in magazijnierSession
        }
        return new ModelAndView("bestelbon")
                .addObject("bestelId", magazijnierSession.getBestelId())
                .addObject("lijstVanBesteldeArtikels", magazijnierSession.getLijstVanBesteldeArtikels());
    }

    @PostMapping("checkbox")
    public String checkbox(@RequestBody MagazijnplaatsIdEnStatus magazijnplaatsIdEnStatus, RedirectAttributes redirect) {
        // Nu nog session aanpassen
        if (!magazijnierSession.setStatusBesteldArtikel(magazijnplaatsIdEnStatus.magazijnplaatsId(), magazijnplaatsIdEnStatus.status())) {
            redirect.addAttribute("BesteldArtikelNietGevondenOpMagazijnPlaats", magazijnplaatsIdEnStatus.magazijnplaatsId());
        }
        return "redirect:/";
    }

    @PostMapping("afgewerktebestelling")
    public String afgewerkteBestelling(RedirectAttributes redirect){
        if (!magazijnierSession.besteldeArtikelsZijnOpgehaald()) {
            redirect.addAttribute("nogNietAlleArtikelsOpgehaald", true);
            return "redirect:/";
        }
        var model = new ModelAndView("index");
        // service aanspreken, etc
        // volgende user story

        // bestelling succesvol afgewerkt
        redirect.addAttribute("bestellingIsAfgewerkt", true);
        return "redirect:/";
    }
}
