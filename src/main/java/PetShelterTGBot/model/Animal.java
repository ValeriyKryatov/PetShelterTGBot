package PetShelterTGBot.model;

import PetShelterTGBot.constants.Color;
import PetShelterTGBot.constants.PetType;
import PetShelterTGBot.constants.Gender;

import javax.persistence.*;
import lombok.*;

/**
 * Класс животный, в котором передает Id, имя животного, тип животного
 * цвет и пол
 * также аннотации lombok, которые генерируют сеттеры геттеры конструктор
 * и методы hashCode, equals и ToString
 */

@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "pet_type")
    private PetType petType;

    @Column(name = "color")
    private Color color;

    @Column(name = "gender")
    private Gender gender;

    public Animal(String nickName, PetType petType, Color color, Gender gender) {
        setNickName(nickName);
        setPetType(petType);
        setColor(color);
        setGender(gender);
    }

    public void setNickName(String nickName) {
        if (nickName == null || nickName.isEmpty() || nickName.isBlank()) {
            throw new RuntimeException("Имя животного введено некорректно!");
        } else {
            this.nickName = nickName;
        }
    }

    public void setPetType(PetType petType) {
        if (petType == null) {
            throw new RuntimeException("Тип животного введен некорректно!");
        } else {
            this.petType = petType;
        }
    }

    public void setColor(Color color) {
        if (color == null) {
            throw new RuntimeException("Цвет животного введен некорректно!");
        } else {
            this.color = color;
        }
    }

    public void setSex(Gender gender) {
        if (gender == null) {
            throw new RuntimeException("Пол животного введен некорректно!");
        } else {
            this.gender = gender;
        }
    }
}