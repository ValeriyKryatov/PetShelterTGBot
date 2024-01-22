package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.theEnumConstants.Animals;

import java.util.ArrayList;
import java.util.List;
/** Третья клавиатура — обустройство дома для домашнего животного
 * Этап 2. Консультация с потенциальным хозяином животного из приюта */
public class TheThirdKeyboardIsHomeImprovementForAPet {
    public static List<String> getList(Animals enumAnimals) {
        List<String> list = new ArrayList<>();
        list.add("Животное детском возрасте ");
        list.add("/animal" + enumAnimals.getTitle());
        list.add("Взрослое животное");
        list.add("/an adult animal" + enumAnimals.getTitle());
        list.add("Животное с ограниченными возможностями ");
        list.add("/a animal with disabilities" + enumAnimals.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumAnimals.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back2" + enumAnimals.getTitle());
        return list;
    }
}