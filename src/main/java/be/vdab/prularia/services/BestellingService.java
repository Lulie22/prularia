package be.vdab.prularia.services;

import be.vdab.prularia.DTO.TVOverZichtDTO;
import be.vdab.prularia.repositories.BestellingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BestellingService {
    private final BestellingRepository bestellingRepository;

    public BestellingService(BestellingRepository bestellingRepository) {
        this.bestellingRepository = bestellingRepository;
    }
    public List<TVOverZichtDTO> findEersteVijfBestellingen(){
        return bestellingRepository.findEersteVijfBestellingen();
    }

    public int aantalBestellingen(){
        return bestellingRepository.aantalBestellingen();
    }

}
