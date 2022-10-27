package be.vdab.prularia.services;

import be.vdab.prularia.domain.Bestellijn;
import be.vdab.prularia.repositories.BestellijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BestellijnService {
    private final BestellijnRepository bestellijnRepository;

    public BestellijnService(BestellijnRepository bestellijnRepository) {
        this.bestellijnRepository = bestellijnRepository;
    }
    public List<Bestellijn> vindBestellijnenByBestelId(long bestelId){
        return bestellijnRepository.vindBestellijnenByBestelId(bestelId);
    }
}
