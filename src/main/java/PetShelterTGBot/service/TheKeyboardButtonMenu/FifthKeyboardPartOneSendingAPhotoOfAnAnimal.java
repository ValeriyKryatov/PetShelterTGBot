package PetShelterTGBot.service.TheKeyboardButtonMenu;

import PetShelterTGBot.constant.PetType;

import java.util.ArrayList;
import java.util.List;

public class FifthKeyboardPartOneSendingAPhotoOfAnAnimal {
    public static List<String> getList(PetType enumPetType) {
        List<String> list = new ArrayList<>();
        list.add("Нажмите кнопку, подтверждения загрузки фото !");
        list.add("/upload photo" + enumPetType.getTitle());
        return list;
    }
}