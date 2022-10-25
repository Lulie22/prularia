package be.vdab.prularia.services;

import be.vdab.prularia.exceptions.LeverancierNietGevondenException;
import be.vdab.prularia.repositories.LeverancierRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeverancierService {
    private final LeverancierRepository leverancierRepository;

    public LeverancierService(LeverancierRepository leverancierRepository) {
        this.leverancierRepository = leverancierRepository;
    }

    public long vindIdByNaam(String naam) {
        Optional<Long> optionalId = leverancierRepository.vindIdByNaam(naam);
        if (optionalId.isEmpty()) {
            throw new LeverancierNietGevondenException();
        } else {
            return optionalId.get();
        }
    }
}
