package PetShelterTGBot.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Создаем сущность "Усыновители"
 */
@Entity
/**
 * Создаем таблицу adopters(Усыновители), имеющую следующие свойства-колонки:
 * 1) id - id усыновителя, генерируемый автоматически,
 * 2) user_id - id пользователя,
 * 3) animal_id - id животного,
 * 4) shelter_id - id приюта
 */
@Table(name = "adopters")
public class Adopter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @OneToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    /**
     * Создаем конструктор с параметрами
     */
    public Adopter(long id, User user, Animal animal, Shelter shelter) {
        this.id = id;
        this.user = user;
        this.animal = animal;
        this.shelter = shelter;
    }

    /**
     * Создаем конструктор без параметров
     */
    public Adopter() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adopter adopter = (Adopter) o;
        return id == adopter.id && Objects.equals(user, adopter.user) && Objects.equals(animal, adopter.animal)
                && Objects.equals(shelter, adopter.shelter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, animal, shelter);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", user=" + user +
                ", animal=" + animal +
                ", shelter=" + shelter +
                '}';
    }
}