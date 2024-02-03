package PetShelterTGBot.service.TheKeyboardButtonMenu;
import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;

/** Четвертая клавиатура — предоставление отчета о животном */
public class TheFourthKeyboardIsToProvideAReadoutOfTheAnimal {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Как правильно предоставлять отчет ");
        list.add("/how to properly submit a report" + enumPetType.getTitle());
        list.add("Начать отчет ");
        list.add("/start report" + enumPetType.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumPetType.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back" + enumPetType.getTitle());
        return list;
    }
}