package PetShelterTGBot.service;

import PetShelterTGBot.model.Adopter;
import PetShelterTGBot.model.Animal;
import PetShelterTGBot.model.Shelter;
import PetShelterTGBot.model.User;

import java.util.Collection;

/**
 * Сервис по работе с усыновителями.
 */
public interface AdopterService {

    /**
     * Сохраняем нового усыновителя
     *
     * @param adopter новый усыновитель
     * @return сохраненный усыновитель
     */
    Adopter saveAdopter(Adopter adopter);

    /**
     * Изменяем усыновителя по его идентификатору
     *
     * @return измененный усыновитель
     */
    void updateAdopterById(Long id,
                           User user,
                           Animal animal,
                           Shelter shelter);

    /**
     * Удаляем усыновителя по его идентификатору
     *
     * @param id идентификатор
     */
    void deleteAdopterById(Long id);

    /**
     * Выводим всех сохраненных усыновителей
     *
     * @return список усыновителей
     */
    Collection<Adopter> getAllAdopter();

    /**
     * Поиск усыновителя по id
     *
     * @param id идентификатор усыновителя
     * @return найденный усыновитель
     */
    Adopter foundAdopterById(long id);
}