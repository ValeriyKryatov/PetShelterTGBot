package PetShelterTGBot.service.thekeyboardbuttonmenu;

import PetShelterTGBot.theenumconstants.Animals;

import java.util.ArrayList;
import java.util.List;

public class TheSecondKeyboardWeTakeAAnimalFromTheShelter {
    public static List<String> getList(Animals enumAnimals) {
        List<String> list = new ArrayList<>();
        list.add("Правила знакомства с питомцем");
        list.add("/rules for getting to know a pet" + enumAnimals.getTitle());
        list.add("Документы, для оформления опекунства");
        list.add("/documents for registration of guardianship" + enumAnimals.getTitle());
        list.add("Транспортировка питомца");
        list.add("/pet transportation" + enumAnimals.getTitle());
        list.add("Обустройство проживания питомца");
        list.add("/arrangement of pet accommodation" + enumAnimals.getTitle());
        list.add("Причины отказа от питомца");
        list.add("/reasons for abandoning a pet"+ enumAnimals.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumAnimals.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back3" + enumAnimals.getTitle());
        return list;
    }
}