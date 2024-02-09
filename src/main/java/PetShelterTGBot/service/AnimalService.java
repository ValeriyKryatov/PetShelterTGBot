package PetShelterTGBot.service;

import PetShelterTGBot.model.Animal;
import PetShelterTGBot.repository.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnimalService {

    private final static Logger logger = LoggerFactory.getLogger(AnimalService.class);

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Animal readAnimal(long id) {
        logger.info("Was invoked method for getting animal by id = {}", id);
        return animalRepository.findById(id).orElse(null);
    }

    public Animal createAnimal(Animal animal) {
        logger.info("Was invoked method for create animal");
        return animalRepository.save(animal);
    }

    public Animal changeAnimal(Animal animal) {
        logger.info("Was invoked method for change animal");
        return animalRepository.save(animal);
    }

    public void deleteAnimal(long id) {
        logger.info("Was invoked method for removing animal by id = {}", id);
        animalRepository.deleteById(id);
    }

    public Collection<Animal> getAllAnimals() {
        logger.info("Was invoked method for getting all animals in shelter");
        return animalRepository.findAll();
    }
}