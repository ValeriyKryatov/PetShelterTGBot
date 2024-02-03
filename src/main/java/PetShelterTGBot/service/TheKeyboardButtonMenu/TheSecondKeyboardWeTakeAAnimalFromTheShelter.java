package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;
/** Вторая клавиатура берем животное из приюта
 * Этап 2. Консультация с потенциальным хозяином животного из приюта */
public class TheSecondKeyboardWeTakeAAnimalFromTheShelter {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Правила знакомства с питомцем");
        list.add("/rules for getting to know a pet" + enumPetType.getTitle());
        list.add("Документы для оформления опекунства");
        list.add("/documents for registration of guardianship" + enumPetType.getTitle());
        list.add("Транспортировка питомца");
        list.add("/pet transportation" + enumPetType.getTitle());
        list.add("Обустройство проживания питомца");
        list.add("/arrangement of pet accommodation" + enumPetType.getTitle());
        list.add("Причины отказа в усыновлении");
        list.add("/reasons for abandoning a pet"+ enumPetType.getTitle());
        if (enumPetType == PetType.DOG) {
        list.add("Советы кинолога");
        list.add("/dog handler advice"+ enumPetType.getTitle());
        list.add("Рекомендации по проверенным кинологам");
        list.add("/recommendations from proven dog handlers" + enumPetType.getTitle());
        }
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumPetType.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back3" + enumPetType.getTitle());
        return list;
    }
}