package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.theEnumConstants.Animals;

import java.util.ArrayList;
import java.util.List;
/** Вторая клавиатура берем животное из приюта
 * Этап 2. Консультация с потенциальным хозяином животного из приюта */
public class TheSecondKeyboardWeTakeAAnimalFromTheShelter {
    public static List<String> getList(Animals enumAnimals) {
        List<String> list = new ArrayList<>();
        list.add("Правила знакомства с питомцем");
        list.add("/rules for getting to know a pet" + enumAnimals.getTitle());
        list.add("Документы для оформления опекунства");
        list.add("/documents for registration of guardianship" + enumAnimals.getTitle());
        list.add("Транспортировка питомца");
        list.add("/pet transportation" + enumAnimals.getTitle());
        list.add("Обустройство проживания питомца");
        list.add("/arrangement of pet accommodation" + enumAnimals.getTitle());
        list.add("Причины отказа в усыновлении");
        list.add("/reasons for abandoning a pet"+ enumAnimals.getTitle());
        list.add("Советы кинолога");
        list.add("/dog handler advice"+ enumAnimals.getTitle());
        list.add("Рекомендации по проверенным кинологам");
        list.add("/recommendations from proven dog handlers" + enumAnimals.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumAnimals.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back3" + enumAnimals.getTitle());
        return list;
    }
}