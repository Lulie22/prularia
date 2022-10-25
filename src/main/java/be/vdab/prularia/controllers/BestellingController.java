package be.vdab.prularia.controllers;

import be.vdab.prularia.exceptions.GeenVolgendeBestellingException;
import be.vdab.prularia.exceptions.OnvoldoendeArtikelInHetMagazijnException;
import be.vdab.prularia.services.BestellingService;
import be.vdab.prularia.sessions.MagazijnierSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    /*@PostMapping("checkbox")
    public void checkedBox(@RequestParam(name = "magazijnplaatsId", defaultValue = "") String stringMagazijnplaatsId,
                           @RequestParam(name = "status", defaultValue = "") String stringStatus) {
        System.out.println("StringmagazijnplaatsId:" + stringMagazijnplaatsId);
        System.out.println("Stringstatus:" + stringStatus);
    }*/
    // Path variables zijn niet mogelijk voor PostMapping
    /*@PostMapping("checkbox/{magazijnplaatsId}/{status}")
    public void checkbox(@PathVariable long magazijnplaatsId, @PathVariable boolean status) {
        System.out.println("magazijnplaatsId:" + magazijnplaatsId);
        System.out.println("status:" + status);
    }*/
    @PostMapping("checkbox")
    @ResponseBody
    public String checkbox(@RequestParam int magazijnplaatsid, @RequestParam String status) {
        System.out.println("StringmagazijnplaatsId:" + magazijnplaatsid);
        System.out.println("Stringstatus:" + status);
        return "magazijnplaatsId: " + magazijnplaatsid;
    }

    @PostMapping("afgewerktebestelling")
    public ModelAndView afgewerkteBestelling(){
        var model = new ModelAndView("index");
        // service aanspreken, etc
        // volgende user story

        // bestelling succesvol afgewerkt
        return model.addObject("bestellingIsAfgewerkt", true);
    }
}
