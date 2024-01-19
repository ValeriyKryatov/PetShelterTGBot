package PetShelterTGBot.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

/**
 * Создаем сущность "Отчеты"
 */
@Entity
/**
 * Создаем таблицу reports(Отчеты), имеющую следующие свойства-колонки:
 * 1) id - id отчета, генерируемый автоматически,
 * 2) userId - id пользователя,
 * 3) dateReport - дата составления отчета,
 * 4) statusReport - статус отчета,
 * 5) dateEndOfProbation - дата окончания испытательного срока,
 * 6) reportText - содержание отчета
 * 7) photoAnimal - фото животного
 */
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "date_report")
    private LocalDate dateReport;

    @Column(name = "status_report")
    private String statusReport;

    @Column(name = "date_end_of_probation")
    private LocalDate dateEndOfProbation;

    @Column(name = "report_text")
    private String reportText;

    @Column(name = "photo_animal")
    private byte[] photoAnimal;

    /**
     * Создаем конструктор с параметрами
     */
    public Report(long id, User userId, LocalDate dateReport, String statusReport, LocalDate dateEndOfProbation, String reportText, byte[] photoAnimal) {
        this.id = id;
        this.userId = userId;
        this.dateReport = dateReport;
        this.statusReport = statusReport;
        this.dateEndOfProbation = dateEndOfProbation;
        this.reportText = reportText;
        this.photoAnimal = photoAnimal;
    }

    /**
     * Создаем конструктор без параметров
     */
    public Report() {
    }

    /**
     * Создаем методы - геттеры/сеттеры/equals/hashCode/toString
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDate getDateReport() {
        return dateReport;
    }

    public void setDateReport(LocalDate dateReport) {
        this.dateReport = dateReport;
    }

    public String getStatusReport() {
        return statusReport;
    }

    public void setStatusReport(String statusReport) {
        this.statusReport = statusReport;
    }

    public LocalDate getDateEndOfProbation() {
        return dateEndOfProbation;
    }

    public void setDateEndOfProbation(LocalDate dateEndOfProbation) {
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public byte[] getPhotoAnimal() {
        return photoAnimal;
    }

    public void setPhotoAnimal(byte[] photoAnimal) {
        this.photoAnimal = photoAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && Objects.equals(userId, report.userId) && Objects.equals(dateReport, report.dateReport) && Objects.equals(statusReport, report.statusReport) && Objects.equals(dateEndOfProbation, report.dateEndOfProbation) && Objects.equals(reportText, report.reportText) && Arrays.equals(photoAnimal, report.photoAnimal);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, userId, dateReport, statusReport, dateEndOfProbation, reportText);
        result = 31 * result + Arrays.hashCode(photoAnimal);
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateReport=" + dateReport +
                ", statusReport='" + statusReport + '\'' +
                ", dateEndOfProbation=" + dateEndOfProbation +
                ", reportText='" + reportText + '\'' +
                ", photoAnimal=" + Arrays.toString(photoAnimal) +
                '}';
    }
}