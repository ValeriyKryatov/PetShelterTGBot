package PetShelterTGBot.service;

import PetShelterTGBot.model.VisitToShelter;
import PetShelterTGBot.repository.VisitToShelterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VisitToShelterService {

    private final static Logger logger = LoggerFactory.getLogger(VisitToShelterService.class);

    private final VisitToShelterRepository visitToShelterRepository;

    public VisitToShelterService(VisitToShelterRepository visitToShelterRepository) {
        this.visitToShelterRepository = visitToShelterRepository;
    }

    public void deleteVisitToShelter(long id) {
        logger.info("Was invoked method for removing visit to shelter by id = {}", id);
        visitToShelterRepository.deleteById(id);
    }

    public Collection<VisitToShelter> getAllVisitToShelter() {
        logger.info("Was invoked method for getting all visit to shelter in shelter");
        return visitToShelterRepository.findAll();
    }
}