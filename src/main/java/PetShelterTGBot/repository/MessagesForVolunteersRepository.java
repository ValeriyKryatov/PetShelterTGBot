package PetShelterTGBot.repository;

import PetShelterTGBot.model.MessagesForVolunteers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesForVolunteersRepository extends JpaRepository<MessagesForVolunteers, Long> {
}