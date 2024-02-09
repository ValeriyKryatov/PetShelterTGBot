package PetShelterTGBot.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Создаем сущность "Пользователи"
 */
@Entity
/**
 * Создаем таблицу users(Пользователи), имеющую следующие свойства-колонки:
 * 1) id - id пользователя, генерируемый автоматически,
 * 2) firstName - имя пользователя,
 * 3) lastName - фамилия пользователя,
 * 4) telegramId - id пользователя в телеграмм,
 * 5) telegramNick - ник пользователя в телеграмм,
 * 6) phoneNumber - номер телефона пользователя,
 * 7) email - адрес электронной почты пользователя,
 * 8) address - адрес проживания пользователя,
 * 9) carNumber - регистрационный номер автомобиля пользователя,
 * 10) shelterType - тип приюта(для собак/для кошек),
 * 11) userType - тип пользователя(гость/усыновитель/волонтер),
 * 12) userStatus - статус пользователя(подтвержденный/заблокированный)
 */
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "telegram_id")
    private long telegramId;
    @Column(name = "telegram_nick")
    private String telegramNick;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String address;
    @Column(name = "car_number")
    private String carNumber;
    @Column(name = "shelter_type")
    private String shelterType;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "user_status")
    private String userStatus;

    /**
     * Создаем конструктор с параметрами
     */
    public User(long id, String firstName, String lastName,
                long telegramId, String telegramNick,
                String phoneNumber, String email,
                String address, String carNumber,
                String shelterType, String userType, String userStatus) {
        this.id = id;
        setFirstName(firstName);
        setLastName(lastName);
        this.lastName = lastName;
        this.telegramId = telegramId;
        this.telegramNick = telegramNick;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.carNumber = carNumber;
        this.shelterType = shelterType;
        this.userType = userType;
        this.userStatus = userStatus;
    }

    /**
     * Создаем конструктор без параметров
     */
    public User() {
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

    public String getFirstName() {
        return firstName;
    }

    /**
     * Дополнительная проверка корректности введенного ИМЯ пользователя
     */
    public void setFirstName(String firstName) {
        if (firstName.matches("^[a-zA-Zа-яА-Я]+$")
                && Character.isUpperCase(firstName.charAt(0))) {
            this.firstName = firstName;
        } else {
            throw new IllegalArgumentException("Имя пользователя введено некорректно!");
        }
    }

    public String getLastName() {
        return lastName;
    }

    /**
     * Дополнительная проверка корректности введенной ФАМИЛИИ пользователя
     */
    public void setLastName(String lastName) {
        if (lastName.matches("^[a-zA-Zа-яА-Я]+$")
                && Character.isUpperCase(lastName.charAt(0))) {
            this.lastName = lastName;
        } else {
            throw new IllegalArgumentException("Фамилия пользователя введена некорректно!");
        }
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long telegramId) {
        this.telegramId = telegramId;
    }

    public String getTelegramNick() {
        return telegramNick;
    }

    public void setTelegramNick(String telegramNick) {
        this.telegramNick = telegramNick;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getShelterType() {
        return shelterType;
    }

    public void setShelterType(String shelterType) {
        this.shelterType = shelterType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return id == users.id && telegramId == users.telegramId && Objects.equals(firstName, users.firstName)
                && Objects.equals(lastName, users.lastName) && Objects.equals(telegramNick, users.telegramNick)
                && Objects.equals(phoneNumber, users.phoneNumber) && Objects.equals(email, users.email)
                && Objects.equals(address, users.address) && Objects.equals(carNumber, users.carNumber)
                && Objects.equals(shelterType, users.shelterType) && Objects.equals(userType, users.userType)
                && Objects.equals(userStatus, users.userStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, telegramId, telegramNick,
                phoneNumber, email, address, carNumber, shelterType, userType, userStatus);
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telegramId=" + telegramId +
                ", telegramNick='" + telegramNick + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", shelterType='" + shelterType + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}