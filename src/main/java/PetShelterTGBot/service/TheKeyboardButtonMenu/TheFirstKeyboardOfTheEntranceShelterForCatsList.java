package PetShelterTGBot.service.TheKeyboardButtonMenu;

import java.util.ArrayList;
import java.util.List;

public class TheFirstKeyboardOfTheEntranceShelterForCatsList {
    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("Информация о приюте ");
        list.add("/information about the shelter");
        list.add("Как взять питомца из приюта");
        list.add("/how to take a pet from a shelter");
        list.add("Прислать отчет о питомце ");
        list.add("/send a pet report");
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer");
        return list;
    }
}