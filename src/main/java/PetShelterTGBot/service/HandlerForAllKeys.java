package PetShelterTGBot.service;

import PetShelterTGBot.service.TheKeyboardButtonMenu.*;
import PetShelterTGBot.theEnumConstants.Animals;

import static PetShelterTGBot.constant.Constant.*;

public class HandlerForAllKeys {
    public static void keyboardAndMenuHandler(String messageText, long chatId, TelegramBot bot) {
        String textOne = "";
        String textTwo = "";
        if (messageText != null && messageText.contains("#")) {
            String[] arrayString = messageText.split("#");
            textOne = arrayString[0];
            textTwo = arrayString[1];
        }
        switch (textTwo) {
            case "cat":

                switch (textOne) {
                    case "/information about the shelter":
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheFirstKeyboardOfTheEntranceToTheShelterForAnimal.getList(Animals.CAT), bot));
                        break;

                    case "/how to take a pet from a shelter": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheSecondKeyboardWeTakeAAnimalFromTheShelter.getList(Animals.CAT), bot));
                    }
                    break;

                    case "/send a pet report": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheFourthKeyboardIsToProvideAReadoutOfTheAnimal.getList(Animals.CAT), bot));
                    }
                    break;

                    case "/shelter address":
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SHELTER_CAT_ADDRESS);
                        // выводим в бот фотографию
                        bot.sendPhoto(chatId);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.CAT.getTitle()), bot));
                        break;

                    case "/car pass": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, REGISTRATION_OF_A_CAR_PASS_CAT_SHELTER);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/safety precautions": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SAFETY_PRECAUTIONS_CAT_SHELTER);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/visit to the shelter": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, "Чтобы записаться на посещение,\n" +
                                "нужно заполнить анкету:\n" +
                                "Напишите Ваше Имя, Фамилию, номер телефона(без кода страны), номер машины в формате:\n" +
                                "Иван Иванов 1234567890 а123аа   НАПИСАТЬ ВАЛИДАЦИЮ и ОБРАБОТЧИК !!!!");
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/rules for getting to know a pet":
                        bot.sendMessage(chatId, ADVANTAGE_OF_TREATMENT_CAT_SHELTER);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.CAT.getTitle()), bot));
                        break;

                    case "/documents for registration of guardianship": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, BASIC_TERMS);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/pet transportation": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PET_DELIVERY);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/arrangement of pet accommodation": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheThirdKeyboardIsHomeImprovementForAPet.getList(Animals.CAT), bot));
                    }
                    break;

                    case "/reasons for abandoning a pet": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, REASONS_FOR_REFUSAL_TO_ADOPTION);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/animal":
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PREPARING_THE_HOUSE_FOR_THE_ARRIVAL_OF_AN_CAT);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/arrangement of pet accommodation" + Animals.CAT.getTitle()), bot));
                        break;

                    case "/an adult animal": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PREPARING_CONDITIONS_FOR_THE_ARRIVAL_OF_AN_CAT);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/arrangement of pet accommodation" + Animals.CAT.getTitle()), bot));
                    break;

                    case "/a animal with disabilities": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, CAT_WITH_DISABILITIES);
                    }
                    break;

                    case "/how to properly submit a report": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SUBMISSION_OF_THE_REPORT);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/send a pet report" + Animals.CAT.getTitle()), bot));

                    }
                    break;

                    case "/start report": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, UPLOAD_PHOTO);
//                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
//                                GREETINGS_AT_THE_SHELTER_INFO,
//                                FifthKeyboardPartOneSendingAPhotoOfAnAnimal.getList(Animals.CAT), bot));
                        bot.enablingThe_processingPhotosForReport_method = true;
                        bot.animalsFlag = Animals.CAT;
                    }
                    break;

                    case "/animal not photo": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, PHOTO_IS_NOT_UPLOADED,
                                TheReturnToKeyboardButton.getList("/send a pet report" + Animals.CAT.getTitle()), bot));
                    }
                    break;

                    case "/animal diet": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Вы успешно загрузили фотографию питомца." +
                                "Ни чего вводить в строку как внизу бота, так и при загрузке фото, не нужно ! ");
                        bot.sendMessage(chatId, " Запишите рацион животного, чем Вы его кормили ");
                        bot.enablingThe_processingAnimalDiet_method = true;
                        bot.animalsFlag = Animals.CAT;
                    }
                    break;

                    case "/well-being and addiction": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Успешно заполнили рацион животного ");
                        bot.sendMessage(chatId, " Опишите общее самочувствие и привыкание к новому месту животного .");
                        bot.enablingThe_processingWellBeingAndAddiction_method = true;
                        bot.animalsFlag = Animals.CAT;
                    }
                    break;

                    case "/contact a volunteer": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, "Связаться с волонтером нужно написать сообщение сюда, предоставить свои координаты для связи " +
                                "НАПИСАТЬ ОБРАБОТЧИК и ВАЛИДАЦИЮ");
                    }
                    break;

                    case "/come back": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.actionSelectorFromUpdate("/menu1", chatId); // переходим в предыдущее меню

                    }
                    break;

                    case "/come back1", "/come back2": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
                                GREETINGS_AT_THE_SHELTER_INFO,
                                TheSecondKeyboardWeTakeAAnimalFromTheShelter.getList(Animals.CAT), bot));
                    }
                    break;
                    case "/come back3": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
                                GREETINGS_AT_THE_SHELTER_INFO,
                                TheThirdKeyboardIsHomeImprovementForAPet.getList(Animals.CAT), bot)); // переходим в предыдущее меню

                    }
                    break;
                }
                break;

            case "dog":
//               наши обоработчики для собак
                switch (textOne) {
                    case "/information about the shelter":
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheFirstKeyboardOfTheEntranceToTheShelterForAnimal.getList(Animals.DOG), bot));
                        break;
                    case "/how to take a pet from a shelter": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheSecondKeyboardWeTakeAAnimalFromTheShelter.getList(Animals.DOG), bot));
                    }
                    break;

                    case "/send a pet report": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheFourthKeyboardIsToProvideAReadoutOfTheAnimal.getList(Animals.DOG), bot));
                    }
                    break;

                    case "/shelter address":
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SHELTER_DOG_ADDRESS);
                        // выводим в бот фотографию
                        bot.sendPhoto(chatId);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.DOG.getTitle()), bot));
                        break;

                    case "/car pass": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, REGISTRATION_OF_A_CAR_PASS_DOG_SHELTER);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/safety precautions": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SAFETY_PRECAUTIONS_DOG_SHELTER);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/visit to the shelter": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, "Чтобы записаться на посещение,\n" +
                                "нужно заполнить анкету:\n" +
                                "Напишите Ваше Имя, Фамилию, номер телефона(без кода страны), номер машины в формате:\n" +
                                "Иван Иванов 1234567890 а123аа   НАПИСАТЬ ВАЛИДАЦИЮ и ОБРАБОТЧИК !!!!");
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/information about the shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/rules for getting to know a pet":
                        bot.sendMessage(chatId, ADVANTAGE_OF_TREATMENT_DOG_SHELTER);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                        break;

                    case "/documents for registration of guardianship": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, BASIC_TERMS);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/pet transportation": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PET_DELIVERY);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/arrangement of pet accommodation": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                                TheThirdKeyboardIsHomeImprovementForAPet.getList(Animals.DOG), bot));
                    }
                    break;

                    case "/reasons for abandoning a pet": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, REASONS_FOR_REFUSAL_TO_ADOPTION);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/dog handler advice": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, DOG_HANDLER_ADVICE);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/recommendations from proven dog handlers": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, RECOMMENDATIONS_FROM_PROVEN_DOG_HANDLERS);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/how to take a pet from a shelter" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/animal":
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PREPARING_THE_HOUSE_FOR_THE_ARRIVAL_OF_AN_DOG);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/arrangement of pet accommodation" + Animals.DOG.getTitle()), bot));
                        break;
                    case "/an adult animal": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, PREPARING_CONDITIONS_FOR_THE_ARRIVAL_OF_AN_DOG);
                    }
                    bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                            TheReturnToKeyboardButton.getList("/arrangement of pet accommodation" + Animals.DOG.getTitle()), bot));
                    break;

                    case "/a animal with disabilities": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, DOG_WITH_DISABILITIES);
                    }
                    break;

                    // ПЕРЕРАБОТАТЬ ДЛЯ СОБАК!!!!!!
                    case "/how to properly submit a report": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, SUBMISSION_OF_THE_REPORT);
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, RETURN_TO_MENU,
                                TheReturnToKeyboardButton.getList("/send a pet report" + Animals.DOG.getTitle()), bot));

                    }
                    break;

                    case "/start report": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, UPLOAD_PHOTO);
//                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
//                                GREETINGS_AT_THE_SHELTER_INFO,
//                                FifthKeyboardPartOneSendingAPhotoOfAnAnimal.getList(Animals.CAT), bot));
                        bot.enablingThe_processingPhotosForReport_method = true;
                        bot.animalsFlag = Animals.DOG;
                    }
                    break;

                    case "/animal not photo": {
                        // обработчик при этом условии
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId, PHOTO_IS_NOT_UPLOADED,
                                TheReturnToKeyboardButton.getList("/send a pet report" + Animals.DOG.getTitle()), bot));
                    }
                    break;

                    case "/animal diet": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Вы успешно загрузили фотографию питомца." +
                                "Ни чего вводить в строку как внизу бота, так и при загрузке фото, не нужно ! ");
                        bot.sendMessage(chatId, " Запишите рацион животного, чем Вы его кормили ");
                        bot.enablingThe_processingAnimalDiet_method = true;
                        bot.animalsFlag = Animals.DOG;
                    }
                    break;

                    case "/well-being and addiction": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Успешно заполнили рацион животного ");
                        bot.sendMessage(chatId, " Опишите общее самочувствие и привыкание к новому месту животного .");
                        bot.enablingThe_processingWellBeingAndAddiction_method = true;
                        bot.animalsFlag = Animals.DOG;
                    }
                    break;

                    case "/contact a volunteer": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, "Связаться с волонтером нужно написать сообщение сюда, предоставить свои координаты для связи " +
                                "НАПИСАТЬ ОБРАБОТЧИК и ВАЛИДАЦИЮ");
                    }
                    break;

                    case "/come back": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.actionSelectorFromUpdate("/menu1", chatId); // переходим в предыдущее меню

                    }
                    break;

                    case "/come back1", "/come back2": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
                                GREETINGS_AT_THE_SHELTER_INFO,
                                TheSecondKeyboardWeTakeAAnimalFromTheShelter.getList(Animals.DOG), bot));
                    }
                    break;
                    case "/come back3": {
                        // обработчик при этом условии
                        bot.sendMessage(chatId, " Переходим в предыдущее меню");
                        bot.sendMessage(bot.getProjectKeyboardConverter().inLineKeyboard(chatId,
                                GREETINGS_AT_THE_SHELTER_INFO,
                                TheThirdKeyboardIsHomeImprovementForAPet.getList(Animals.DOG), bot)); // переходим в предыдущее меню

                    }
                    break;
                }
                break;
        }
    }
}