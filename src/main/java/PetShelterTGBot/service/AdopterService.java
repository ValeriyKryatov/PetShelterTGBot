package PetShelterTGBot.service;

import PetShelterTGBot.model.Adopter;
import PetShelterTGBot.repository.AdopterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdopterService {

    private final static Logger logger = LoggerFactory.getLogger(AdopterService.class);

    private final AdopterRepository adopterRepository;

    public AdopterService(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    public Adopter readAdopter(long id) {
        logger.info("Was invoked method for getting adopter by id = {}", id);
        return adopterRepository.findById(id).orElse(null);
    }

    public Adopter createAdopter(Adopter adopter) {
        logger.info("Was invoked method for create adopter");
        return adopterRepository.save(adopter);
    }

    public Adopter changeAdopter(Adopter adopter) {
        logger.info("Was invoked method for change adopter");
        return adopterRepository.save(adopter);
    }

    public void deleteAdopter(long id) {
        logger.info("Was invoked method for removing adopter by id = {}", id);
        adopterRepository.deleteById(id);
    }

    public Collection<Adopter> getAllAdopters() {
        logger.info("Was invoked method for getting all adopters in shelter");
        return adopterRepository.findAll();
    }
}