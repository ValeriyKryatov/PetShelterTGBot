package PetShelterTGBot.repositrory;

import PetShelterTGBot.constants.*;
import PetShelterTGBot.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    /**
     * Изменяем животное по идентификатору
     *
     * @param id       идентификатор
     * @param nickName кличка
     * @param petType  тип животного
     * @param color    цвет
     * @param gender      пол
     */
    @Modifying
    @Query("UPDATE Animal a SET " +
            "a.nickName = :nick_name, " +
            "a.petType = :pet_type," +
            "a.color = :color," +
            "a.gender = :gender" +
            " WHERE a.id = :id")
    void updateAnimalById(
            @Param("id") Long id,
            @Param("nick_name") String nickName,
            @Param("pet_type") PetType petType,
            @Param("color") Color color,
            @Param("gender") Gender gender);
}