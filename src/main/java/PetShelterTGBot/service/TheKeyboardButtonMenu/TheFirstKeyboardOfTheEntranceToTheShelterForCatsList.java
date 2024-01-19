package PetShelterTGBot.service.TheKeyboardButtonMenu;

import java.util.ArrayList;
import java.util.List;

public class TheFirstKeyboardOfTheEntranceToTheShelterForCatsList {
    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Информация о приюте ");
        list.add("/shelter address");
        list.add("Оформить пропуск для автомобиля ");
        list.add("/car pass");
        list.add("Техника безопасности ");
        list.add("/safety precautions");
        list.add("Записаться на посещение приюта  ");
        list.add("/visit to the shelter");
        list.add("Связаться с волонтером приюта  ");
        list.add("/contact a volunteer3");
        list.add("Вернуться в меню назад  ");
        list.add("/come back");
        return list;
    }
}