package PetShelterTGBot.model;

import PetShelterTGBot.theEnumConstants.Animals;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

@Slf4j
@Service
/** класс - утилита переменных нужный для подготовки отчетов и сообщений пользователя */
public class UserState {
    /**    индификатор, пользователя - id в боте  */
    private long chatId = 0;
    /**    флаг, определяет какому виду принадлежит животное  */
    private Animals animalsFlag;
    /**    имя, пользователя телеграм бота */
    private String nameUser = "";
    /**   фото питомца, для заполнения отчета */
    private PhotoSize photoSize;

    /** Диета и питание питомца */
    private String animalDiet;

    /** Самочувствие питомца */
    private String WellBeingAndAddiction;

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Animals getAnimalsFlag() {
        return animalsFlag;
    }

    public void setAnimalsFlag(Animals animalsFlag) {
        this.animalsFlag = animalsFlag;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public PhotoSize getPhotoSize() {
        return photoSize;
    }

    public void setPhotoSize(PhotoSize photoSize) {
        this.photoSize = photoSize;
    }

    public String getAnimalDiet() {
        return animalDiet;
    }

    public void setAnimalDiet(String animalDiet) {
        this.animalDiet = animalDiet;
    }

    public String getWellBeingAndAddiction() {
        return WellBeingAndAddiction;
    }

    public void setWellBeingAndAddiction(String wellBeingAndAddiction) {
        WellBeingAndAddiction = wellBeingAndAddiction;
    }
}
