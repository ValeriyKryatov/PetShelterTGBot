package PetShelterTGBot.service.TheKeyboardButtonMenu;

import java.util.ArrayList;
import java.util.List;

public class TheSecondKeyboardWeTakeACatFromTheShelterList {
    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Правила знакомства с питомцем");
        list.add("/rules for getting to know a pet");
        list.add("Документы, для оформления опекунства");
        list.add("/documents for registration of guardianship");
        list.add("Транспортировка питомца");
        list.add("/pet transportation");
        list.add("Обустройство проживания питомца");
        list.add("/arrangement of pet accommodation");
        list.add("Причины отказа от питомца");
        list.add("/reasons for abandoning a pet");
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer2");
        return list;
    }
}