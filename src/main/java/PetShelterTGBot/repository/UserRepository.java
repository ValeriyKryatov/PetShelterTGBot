package PetShelterTGBot.repository;

import PetShelterTGBot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}