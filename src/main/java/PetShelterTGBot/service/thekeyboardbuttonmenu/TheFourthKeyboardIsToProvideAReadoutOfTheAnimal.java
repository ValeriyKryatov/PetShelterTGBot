package PetShelterTGBot.service.thekeyboardbuttonmenu;

import PetShelterTGBot.theenumconstants.Animals;

import java.util.ArrayList;
import java.util.List;


public class TheFourthKeyboardIsToProvideAReadoutOfTheAnimal {
    public static List<String> getList(Animals enumAnimals) {
        List<String> list = new ArrayList<>();
        list.add("Как правильно предоставлять отчет ");
        list.add("/how to properly submit a report" + enumAnimals.getTitle());
        list.add("Начать отчет ");
        list.add("/start report" + enumAnimals.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumAnimals.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back" + enumAnimals.getTitle());
        return list;
    }
}