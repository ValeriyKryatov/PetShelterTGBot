package PetShelterTGBot.service.thekeyboardbuttonmenu;

import PetShelterTGBot.theenumconstants.Animals;

import java.util.ArrayList;
import java.util.List;

public class TheFirstKeyboardOfTheEntranceToTheShelterForAnimal {
   public static List<String> getList(Animals enumAnimals) {
      List<String> list = new ArrayList<>();
      list.add("Адрес приюта ");
      list.add("/shelter address" + enumAnimals.getTitle());
      list.add("Оформить пропуск для автомобиля ");
      list.add("/car pass" + enumAnimals.getTitle());
      list.add("Техника безопасности в приюте ");
      list.add("/safety precautions" + enumAnimals.getTitle());
      list.add("Записаться на посещение приюта  ");
      list.add("/visit to the shelter" + enumAnimals.getTitle());
      list.add("Связаться с волонтером приюта  ");
      list.add("/contact a volunteer" + enumAnimals.getTitle());
      list.add("Вернуться в меню назад  ");
      list.add("/come back" + enumAnimals.getTitle());
      return list;
   }
}