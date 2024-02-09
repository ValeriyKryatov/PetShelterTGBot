package PetShelterTGBot.model;


import jakarta.persistence.*;

import java.util.Objects;

/**
 * Создаем сущность "Приюты"
 */
@Entity
/**
 * Создаем таблицу animals(Приюты), имеющую следующие свойства-колонки:
 * 1) id - id приюта, генерируемый автоматически,
 * 2) addressShelter - адрес приюта,
 * 3) timeWork - время работы приюта,
 * 4) drivingDirections - маршруты проезда,
 * 5) phoneShelter - номер телефона приюта,
 * 6) phoneSecurity - номер телефона охраны,
 * 7) shelterType - тип приюта(для кошек/для собак)
 */
@Table(name = "shelters")
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "address_shelter")
    private String addressShelter;
    @Column(name = "time_work")
    private String timeWork;

    @Column(name = "driving_directions")
    private String drivingDirections;
    @Column(name = "phone_shelter")
    private String phoneShelter;

    @Column(name = "phone_security")
    private String phoneSecurity;

    @Column(name = "shelter_type")
    private String shelterType;

    /**
     * Создаем конструктор с параметрами
     */
    public Shelter(long id, String addressShelter, String timeWork, String drivingDirections,
                   String phoneShelter, String phoneSecurity, String shelterType) {
        this.id = id;
        this.addressShelter = addressShelter;
        this.timeWork = timeWork;
        this.drivingDirections = drivingDirections;
        this.phoneShelter = phoneShelter;
        this.phoneSecurity = phoneSecurity;
        this.shelterType = shelterType;
    }

    /**
     * Создаем конструктор без параметров
     */
    public Shelter() {
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

    public String getAddressShelter() {
        return addressShelter;
    }

    public void setAddressShelter(String addressShelter) {
        this.addressShelter = addressShelter;
    }

    public String getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(String timeWork) {
        this.timeWork = timeWork;
    }

    public String getDrivingDirections() {
        return drivingDirections;
    }

    public void setDrivingDirections(String drivingDirections) {
        this.drivingDirections = drivingDirections;
    }

    public String getPhoneShelter() {
        return phoneShelter;
    }

    public void setPhoneShelter(String phoneShelter) {
        this.phoneShelter = phoneShelter;
    }

    public String getPhoneSecurity() {
        return phoneSecurity;
    }

    public void setPhoneSecurity(String phoneSecurity) {
        this.phoneSecurity = phoneSecurity;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return id == shelter.id && Objects.equals(addressShelter, shelter.addressShelter)
                && Objects.equals(timeWork, shelter.timeWork) && Objects.equals(drivingDirections, shelter.drivingDirections)
                && Objects.equals(phoneShelter, shelter.phoneShelter) && Objects.equals(phoneSecurity, shelter.phoneSecurity)
                && Objects.equals(shelterType, shelter.shelterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressShelter, timeWork, drivingDirections, phoneShelter, phoneSecurity, shelterType);
    }

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", addressShelter='" + addressShelter + '\'' +
                ", timeWork='" + timeWork + '\'' +
                ", drivingDirections='" + drivingDirections + '\'' +
                ", phoneShelter='" + phoneShelter + '\'' +
                ", phoneSecurity='" + phoneSecurity + '\'' +
                ", shelterType='" + shelterType + '\'' +
                '}';
    }
}