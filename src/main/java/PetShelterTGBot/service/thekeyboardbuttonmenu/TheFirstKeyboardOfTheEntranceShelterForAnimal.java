package PetShelterTGBot.service.thekeyboardbuttonmenu;

import  PetShelterTGBot.theenumconstants.Animals;

import java.util.ArrayList;
import java.util.List;

public class TheFirstKeyboardOfTheEntranceShelterForAnimal {
    public static List<String> getList(Animals enumAnimals) {
        List<String> list = new ArrayList<>();
        list.add("Информация о приюте ");
        list.add("/information about the shelter" + enumAnimals.getTitle());
        list.add("Как взять питомца из приюта");
        list.add("/how to take a pet from a shelter" + enumAnimals.getTitle());
        list.add("Прислать отчет о питомце ");
        list.add("/send a pet report" + enumAnimals.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumAnimals.getTitle());
        return list;
    }
}