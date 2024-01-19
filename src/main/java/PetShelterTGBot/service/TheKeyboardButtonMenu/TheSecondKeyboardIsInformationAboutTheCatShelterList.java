package PetShelterTGBot.service.TheKeyboardButtonMenu;
import java.util.ArrayList;
import java.util.List;
public class TheSecondKeyboardIsInformationAboutTheCatShelterList {
    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Адрес места нахождения приюта ");
        list.add("/shelter address");
        list.add("Оформить пропуск для автомобиля ");
        list.add("/car pass");
        list.add("Техника безопасности ");
        list.add("/safety precautions");
        list.add("Записаться на посещение приюта  ");
        list.add("/visit to the shelter");
        list.add("Связаться с волонтером приюта  ");
        list.add("/contact a volunteer");
        list.add("Вернуться в меню назад  ");
        list.add("/come back1");
        return list;
    }
}