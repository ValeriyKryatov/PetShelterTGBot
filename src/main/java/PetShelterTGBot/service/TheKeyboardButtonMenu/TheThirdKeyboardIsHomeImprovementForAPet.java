package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;
/** Третья клавиатура — обустройство дома для домашнего животного
 * Этап 2. Консультация с потенциальным хозяином животного из приюта */
public class TheThirdKeyboardIsHomeImprovementForAPet {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Животное в детском возрасте ");
        list.add("/animal" + enumPetType.getTitle());
        list.add("Взрослое животное");
        list.add("/an adult animal" + enumPetType.getTitle());
        list.add("Животное с ограниченными возможностями ");
        list.add("/a animal with disabilities" + enumPetType.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumPetType.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back2" + enumPetType.getTitle());
        return list;
    }
}