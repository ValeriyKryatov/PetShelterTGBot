package PetShelterTGBot.model;

import PetShelterTGBot.theenumconstants.Animals;
;
import lombok.Getter;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Создаем сущность "Отчеты"
 * Создаем таблицу reports(Отчеты), имеющую следующие свойства-колонки:
 * 1) long id - id отчета, генерируемый автоматически,
 * 2) long chatId - id пользователя,
 * 3) String nameUser - имя, пользователя телеграм бота,
 * 4) Date dateReport - дата составления отчета,
 * 5) Date dateEndOfProbation - дата окончания испытательного срока,
 * 6) int statusReport - статус отчета,
 * 6) String reportText - содержание отчета,
 * 7) PhotoSize photoAnimal - фото питомца, для заполнения отчета,
 * 8) Animals animalsFlag - флаг который указывает к какому типу относится животное,
 * 9) String animalDiet - диета и питание питомца,
 * 10) String wellBeingAndAddiction - самочувствие питомца изменение привычек
 */
@Entity
@Table(name = "reports")
public class Report {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //    @ManyToOne
    @JoinColumn(name = "chat_id")
    private long chatId;
    @Column(name = "name_user")
    private String nameUser;
    @Column(name = "date_report")
    private Date dateReport;
    @Column(name = "date_end_of_probation")
    private Date dateEndOfProbation;
    @Column(name = "status_report")
    private int statusReport;
    @Column(name = "report_text")
    private String reportText;
    @Column(name = "photo_animal")
    private byte[] photoAnimal;
    @Column(name = "animals_flag")
    String animalsFlag;

    @Column(name = "animal_diet")
    String animalDiet;
    @Column(name = "well_being_and_addiction")
    String wellBeingAndAddiction;

    /**
     * Создаем конструктор без параметров
     */
    public Report() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getDateReport() {
        return dateReport;
    }

    public void setDateReport(Date dateReport) {
        this.dateReport = dateReport;
    }

    public Date getDateEndOfProbation() {
        return dateEndOfProbation;
    }

    public void setDateEndOfProbation(Date dateEndOfProbation) {
        this.dateEndOfProbation = dateEndOfProbation;
    }

    public void setStatusReport(int statusReport) {
        this.statusReport = statusReport;
    }

    public String getAnimalDiet() {
        return animalDiet;
    }

    public void setAnimalDiet(String animalDiet) {
        this.animalDiet = animalDiet;
    }

    public Animals getAnimalsFlag() {
        switch (this.animalsFlag) {
            case "#cat":
                return Animals.CAT;
            case "#dog":
                return Animals.DOG;
        }
        return null;
    }

    public void setAnimalsFlag(Animals animalsFlag) {
        this.animalsFlag = animalsFlag.getTitle();
    }

    public String getWellBeingAndAddiction() {
        return wellBeingAndAddiction;
    }

    public void setWellBeingAndAddiction(String wellBeingAndAddiction) {
        this.wellBeingAndAddiction = wellBeingAndAddiction;
    }

    public byte[] getPhotoAnimal() {
        return this.photoAnimal;
    }

    public void setPhotoAnimal(byte[] photoAnimal) {
        this.photoAnimal = photoAnimal;
    }

    public int getStatusReport() {
        return statusReport;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getReportText() {
        return reportText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && statusReport == report.statusReport
                && Objects.equals(chatId, report.chatId)
                && Objects.equals(nameUser, report.nameUser)
                && Objects.equals(dateReport, report.dateReport)
                && Objects.equals(dateEndOfProbation, report.dateEndOfProbation)
                && Objects.equals(reportText, report.reportText)
                && Objects.equals(photoAnimal, report.photoAnimal)
                && animalsFlag == report.animalsFlag
                && Objects.equals(animalDiet, report.animalDiet)
                && Objects.equals(wellBeingAndAddiction, report.wellBeingAndAddiction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, nameUser, dateReport,
                dateEndOfProbation, statusReport,
                reportText, photoAnimal, animalsFlag,
                animalDiet, wellBeingAndAddiction);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + chatId +
                ", nameUser='" + nameUser + '\'' +
                ", dateReport=" + dateReport +
                ", dateEndOfProbation=" + dateEndOfProbation +
                ", statusReport=" + statusReport +
                ", reportText='" + reportText + '\'' +
                ", photoAnimal=" + photoAnimal +
                ", animalsFlag=" + animalsFlag +
                ", animalDiet='" + animalDiet + '\'' +
                ", wellBeingAndAddiction='" + wellBeingAndAddiction + '\'' +
                '}';
    }
}