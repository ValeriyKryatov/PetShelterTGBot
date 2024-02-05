package PetShelterTGBot.repository;

import PetShelterTGBot.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report,Long> {
    List<Report> findByDateEndOfProbationBetween(Date dateEndOfProbation, Date dateEndOfProbation2 );

    List<Report>  findAllByStatusReport (int varible);
}
