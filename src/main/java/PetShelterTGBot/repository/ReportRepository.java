package PetShelterTGBot.repository;

import PetShelterTGBot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
}
