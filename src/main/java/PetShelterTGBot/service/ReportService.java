package PetShelterTGBot.service;

import PetShelterTGBot.model.Report;
import PetShelterTGBot.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void deleteReport(long id) {
        logger.info("Was invoked method for removing report by id = {}", id);
        reportRepository.deleteById(id);
    }

    public Collection<Report> getAllReports() {
        logger.info("Was invoked method for getting all reports in shelter");
        return reportRepository.findAll();
    }
}