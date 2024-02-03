package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;
/** Первая клавиатура при входе в приют животных */
public class TheFirstKeyboardOfTheEntranceShelterForAnimal {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Информация о приюте ");
        list.add("/information about the shelter" + enumPetType.getTitle());
        list.add("Как взять питомца из приюта");
        list.add("/how to take a pet from a shelter" + enumPetType.getTitle());
        list.add("Прислать отчет о питомце ");
        list.add("/send a pet report" + enumPetType.getTitle());
        list.add("Связаться с волонтером приюта ");
        list.add("/contact a volunteer" + enumPetType.getTitle());
        return list;
    }
}