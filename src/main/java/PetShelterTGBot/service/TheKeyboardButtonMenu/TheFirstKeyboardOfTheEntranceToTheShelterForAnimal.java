package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;
/** Первая клавиатура входа в приют животных
 * Этап 1. Консультация с новым пользователем  */
public class TheFirstKeyboardOfTheEntranceToTheShelterForAnimal {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Адрес приюта ");
        list.add("/shelter address" + enumPetType.getTitle());
        list.add("Оформить пропуск для автомобиля ");
        list.add("/car pass" + enumPetType.getTitle());
        list.add("Техника безопасности в приюте ");
        list.add("/safety precautions" + enumPetType.getTitle());
        list.add("Записаться на посещение приюта  ");
        list.add("/visit to the shelter" + enumPetType.getTitle());
        list.add("Связаться с волонтером приюта  ");
        list.add("/contact a volunteer" + enumPetType.getTitle());
        list.add("Вернуться в меню назад  ");
        list.add("/come back" + enumPetType.getTitle());
        return list;
    }
}