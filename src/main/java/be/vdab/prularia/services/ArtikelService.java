package be.vdab.prularia.services;

import be.vdab.prularia.domain.Artikel;
import be.vdab.prularia.exceptions.ArtikelNietGevondenException;
import be.vdab.prularia.exceptions.LeverancierNietGevondenException;
import be.vdab.prularia.repositories.ArtikelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtikelService {
    private final ArtikelRepository artikelRepository;


    public ArtikelService(ArtikelRepository artikelRepository) {
        this.artikelRepository = artikelRepository;
    }

    public Artikel vindById(long artikelId) {
        Optional<Artikel> optionalArtikel = artikelRepository.vindById(artikelId);
        return optionalArtikel.orElse(null);
    }

    public Artikel vindByEAN(String ean) {
        Optional<Artikel> optionalArtikel = artikelRepository.vindByEAN(ean);
        if (optionalArtikel.isEmpty()) {
            throw new ArtikelNietGevondenException();
        } else {
            return optionalArtikel.get();
        }
    }
}
