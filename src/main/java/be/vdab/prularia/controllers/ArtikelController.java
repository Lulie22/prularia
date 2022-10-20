package be.vdab.prularia.controllers;

import be.vdab.prularia.services.ArtikelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("artikel")
public class ArtikelController {
    private final ArtikelService artikelService;

    public ArtikelController(ArtikelService artikelService) {
        this.artikelService = artikelService;
    }
    @GetMapping("{artikelId}")
    public ModelAndView getArtikel(@PathVariable long artikelId){
        return new ModelAndView("artikel","artikel",artikelService.vindById(artikelId));
    }
}
