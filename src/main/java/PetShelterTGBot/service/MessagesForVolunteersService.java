package PetShelterTGBot.service;

import PetShelterTGBot.model.MessagesForVolunteers;
import PetShelterTGBot.repository.MessagesForVolunteersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessagesForVolunteersService {

    private final static Logger logger = LoggerFactory.getLogger(MessagesForVolunteersService.class);

    private final MessagesForVolunteersRepository messagesForVolunteersRepository;

    public MessagesForVolunteersService(MessagesForVolunteersRepository messagesForVolunteersRepository) {
        this.messagesForVolunteersRepository = messagesForVolunteersRepository;
    }

    public void deleteMessagesForVolunteers(long id) {
        logger.info("Was invoked method for removing messages for volunteers by id = {}", id);
        messagesForVolunteersRepository.deleteById(id);
    }

    public Collection<MessagesForVolunteers> getAllMessagesForVolunteers() {
        logger.info("Was invoked method for getting all messages for volunteers in shelter");
        return messagesForVolunteersRepository.findAll();
    }
}