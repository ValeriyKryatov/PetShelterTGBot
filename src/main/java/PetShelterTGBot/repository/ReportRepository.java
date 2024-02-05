package PetShelterTGBot.repository;

import PetShelterTGBot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report>  findAllByStatusReport (int statusReport);
    Report findFirstByOrderByIdDesc();

}
