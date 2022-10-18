package be.vdab.prularia.controllers;

import be.vdab.prularia.DTO.TVOverZichtDTO;
import be.vdab.prularia.services.BestellingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("TV")
public class TVOverzichtController {
    private final BestellingService bestellingService;

    public TVOverzichtController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }
    @GetMapping
    public ModelAndView getEerstevijfBestelling(){
        var bestellingen = bestellingService.findEersteVijfBestellingen();
       // var totaalBestellingen = bestellingen.stream().map(TVOverZichtDTO::getAantal).reduce(0,Integer::sum);
        return new ModelAndView("TV","bestellingen", bestellingen)
                .addObject("totaalBestelling",bestellingService.aantalBestellingen());
    }
}
