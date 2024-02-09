package PetShelterTGBot.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Создаем сущность "Животные"
 */
@Entity
/**
 * Создаем таблицу animals(Животные), имеющую следующие свойства-колонки:
 * 1) id - id животного, генерируемый автоматически,
 * 2) nickName - кличка животного,
 * 3) petType - тип домашнего питомца(кошка/собака),
 * 4) color - цвет животного,
 * 5) sex - пол животного,
 * 6) age - возраст животного
 * 7) breed - порода животного
 */
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nick_name")
    private String nickName;
    @Column(name = "pet_type")
    private String petType;
    private String color;
    private String sex;
    private long age;
    private String breed;

    /**
     * Создаем конструктор с параметрами
     */
    public Animal(long id, String nickName, String petType, String color, String sex, long age, String breed) {
        this.id = id;
        this.nickName = nickName;
        this.petType = petType;
        this.color = color;
        this.sex = sex;
        this.age = age;
        this.breed = breed;
    }

    /**
     * Создаем конструктор без параметров
     */
    public Animal() {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animals = (Animal) o;
        return id == animals.id && age == animals.age && Objects.equals(nickName, animals.nickName)
                && Objects.equals(petType, animals.petType) && Objects.equals(color, animals.color)
                && Objects.equals(sex, animals.sex) && Objects.equals(breed, animals.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, petType, color, sex, age, breed);
    }

    @Override
    public String toString() {
        return "Animals{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", petType='" + petType + '\'' +
                ", color='" + color + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                '}';
    }
}