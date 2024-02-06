package PetShelterTGBot.service;

import PetShelterTGBot.model.User;
import PetShelterTGBot.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readUser(long id) {
        logger.info("Was invoked method for getting user by id = {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        logger.info("Was invoked method for create user");
        return userRepository.save(user);
    }

    public User changeUser(User user) {
        logger.info("Was invoked method for change user");
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        logger.info("Was invoked method for removing user by id = {}", id);
        userRepository.deleteById(id);
    }

    public Collection<User> getAllUsers() {
        logger.info("Was invoked method for getting all users in shelter");
        return userRepository.findAll();
    }
}