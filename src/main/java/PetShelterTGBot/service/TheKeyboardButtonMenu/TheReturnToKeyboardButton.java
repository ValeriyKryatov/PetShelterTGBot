package PetShelterTGBot.service.TheKeyboardButtonMenu;

import java.util.ArrayList;
import java.util.List;

public class TheReturnToKeyboardButton {
    public static List<String> getList(String aTextTagForARefund) {
        List<String> list = new ArrayList<>();
        list.add("Вернуться в меню назад  ");
        list.add(aTextTagForARefund);
        return list;
    }
}