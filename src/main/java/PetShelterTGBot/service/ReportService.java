package PetShelterTGBot.service;

import PetShelterTGBot.constants.StatusReport;
import PetShelterTGBot.model.Report;
import PetShelterTGBot.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Сервис по работе с отчетами.
 */
public interface ReportService {

    /**
     * Сохраняем новый отчет
     *
     * @param userId             id пользователя
     * @param dateReport         дата отчета
     * @param statusReport       статус отчета
     * @param textReport         отчет в тексте
     * @param picture            фотоотчет
     * @return новый отчет
     */
    Report saveReport(User userId,
                      LocalDate dateReport,
                      StatusReport statusReport,
                      String textReport,
                      byte[] picture);


    /**
     * Изменяем отчет по id пользователя
     *
     * @param userId             id пользователя
     * @param dateReport         дата отчета
     * @param statusReport       статус отчета
     * @param picture            фотоотчет
     */
    void updateReportByUserId(User userId,
                              LocalDate dateReport,
                              StatusReport statusReport,
                              String textReport,
                              byte[] picture);


    /**
     * Изменяем дату испытательного срока по id пользователя
     * @param userId id пользователя
     * @param dateEndOfProbation дата окончания испытательного срока
     */
    void updateDateEndOfProbationById(User userId,
                                      LocalDate dateEndOfProbation);

    @Transactional
    void updateStatusReportById(User userId,
                                StatusReport statusReport);

    /**
     * Удаляем отчет по его id
     *
     * @param id идентификатор отчета
     */
    void deleteReportById(Long id);

    /**
     * Выводим все отчеты
     *
     * @return список отчетов
     */
    Collection<Report> getAllReport();
}