package be.vdab.prularia.controllers;

import be.vdab.prularia.exceptions.*;
import be.vdab.prularia.services.BestellijnService;
import be.vdab.prularia.services.BestellingService;
import be.vdab.prularia.sessions.MagazijnierSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("bestelling")
public class BestellingController {
    private final BestellingService bestellingService;
    private final MagazijnierSession magazijnierSession;
    private final BestellijnService bestellijnService;

    public BestellingController(BestellingService bestellingService, MagazijnierSession magazijnierSession, BestellijnService bestellijnService) {
        this.bestellingService = bestellingService;
        this.magazijnierSession = magazijnierSession;
        this.bestellijnService = bestellijnService;
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

    @PostMapping("afgewerktebestelling")

    public ModelAndView afgewerktebestelling(RedirectAttributes redirect) {
       // check();

        try {
            var uitgaandeLeveringsId = bestellingService.afgewerkteBestelling();
            redirect.addAttribute("uitgaandeLeveringsId", uitgaandeLeveringsId);
            return new ModelAndView("redirect:/");
        } catch (OnvoldoendeArtikelInHetMagazijnException ex) {
            redirect.addAttribute("onvoldoendeArtikelenInMagazijn", true);
            redirect.addAttribute("onvoldoendeArtikelenBestelId", ex.getBestelId());
            redirect.addAttribute("onvoldoendeArtikelenBestellijnId", ex.getBestellijnId());
            redirect.addAttribute("onvoldoendeArtikelenMagazijnPlaatsId", ex.getMagazijnPlaatsId());
            redirect.addAttribute("onvoldoendeArtikelenArtikelId", ex.getArtikelId());
            return new ModelAndView("redirect:/");
        } catch (OnvoldoendeArtikelVoorraadException ex) {
            //
            redirect.addAttribute("onvoldoendeArtikelVoorraad", true);
            redirect.addAttribute("onvoldoendeArtikelenBestelId", ex.getBestelId());
            redirect.addAttribute("onvoldoendeArtikelenBestellijnId", ex.getBestellijnId());
            redirect.addAttribute("onvoldoendeArtikelenArtikelId", ex.getArtikelId());
            return new ModelAndView("redirect:/");
        } catch (MagazijnPlaatsNietGevondenException ex) {
            redirect.addAttribute("magazijnPlaatsnietGevonden", true);
            return new ModelAndView("redirect:/");
        }

    }
}
