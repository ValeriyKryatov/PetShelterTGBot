package PetShelterTGBot.service;

import PetShelterTGBot.config.BotConfig;
import PetShelterTGBot.service.TheKeyboardButtonMenu.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {
    long chatId = 0;
    /**
     * имя, пользователя телеграм бота
     */
    String nameUser = "";

    /**
     * данный Map, запоминает chatId пользователя и клавиатуру, где он находится в текущий момент
     */
    final Map<Long, List<String>> userAlreadyInteracted = new HashMap<>();

    /**
     * данный Set, запоминает, какие клавиатуры, проходил пользователь и подставляет их в обработку кнопок
     */
    final BotConfig botConfig;

/**   последняя запоминаемая клавиатура в которой был пользователь */

    /**
     * активием клавиатуры проекта
     */
    ProjectKeyboardConverter projectKeyboardConverter;

    public TelegramBot(BotConfig botConfig,
                       ProjectKeyboardConverter projectKeyboardConverter
    ) {
        this.botConfig = botConfig;
        this.projectKeyboardConverter = projectKeyboardConverter;

//     удаляем кнопку Menu слева из командной строки бота и нижнее стационарную клавиатуру
//     removeButtonFromTheBot(); создаем кнопку -> Меню в углу командной строки сообщений
//     бота и отображаем её
        MainMenu.mainMenuButton(this);
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    public String getBotThePathToTheImageFile() {
        return botConfig.getThePathToTheImageFile();
    }

    public ProjectKeyboardConverter getProjectKeyboardConverter() {
        return projectKeyboardConverter;
    }

    /**
     * метод, который принимает "сообщения" (объекты) присланные с телеграмм бота,
     * выделяет нужные нам поля из данных (присланных) объектов и передает эти поля
     * в (actionSelectorFromUpdate(String text, long chatId)) для дальнейшей обработки
     */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("  Вошли в метод ==> onUpdateReceived(Update update) ");
        String messageText;
        if (update.hasMessage() && update.getMessage().hasText()) {
            nameUser = update.getMessage().getChat().getFirstName();
            chatId = update.getMessage().getChatId();
            messageText = update.getMessage().getText();
            actionSelectorFromUpdate(messageText, chatId);
            System.out.println("  Определили имя пользователя бота в update.getMessage().hasText() ==> " + nameUser);
            System.out.println("  Определили chatId для бота update.getMessage().getChatId()  ==> " + chatId);
            System.out.println("  Этот текст, пришел от бота update.getMessage().getText() ==> " + messageText);
        } else if (update.hasCallbackQuery()) {
            messageText = update.getCallbackQuery().getData();
            System.out.println("  Этот текст, пришел от бота в update.getCallbackQuery().getData() ==> " + messageText);
            actionSelectorFromUpdate(messageText, chatId);
            System.out.println("  Выход обработался метод ==> onUpdateReceived(Update update)  ");
        }
    }

    /**
     * метод обработки сообщений (полей) из главного Меню, от метода -> onUpdateReceived(Update update),
     * выбирает (в зависимости от пункта) и выводит в бот, следующую клавиатуру
     */
    public void actionSelectorFromUpdate(String text, long chatId) {
        System.out.println(" Вошли в метод ==>  actionSelectorFromUpdate(String text, long chatId) ==>" + text);
        switch (text) {
            case "/start":
//              Проверка, был ли пользователь в нашем ранее боте, если пользователь впервые - то приветствие,
//                если нет то клавиатура, которую ранее покинул, входящий сейчас пользователь
                sendMessage(userLogsInForTheFirstTime(startStringReceived(nameUser)));
                break;
            case "/menu1": {
//             Вызов, (отображение) клавиатуры привязанной к сообщению в чате
                sendMessage(projectKeyboardConverter.inlineKeyboard(chatId,
                        "Выберете, пожалуйста, вариант из предложенного меню!",
                        TheFirstKeyboardOfTheEntranceShelterForCatsList.getList(), this));
            }
            break;
            case "/menu2": {
//              Вызов, (отображение) клавиатуры привязанной к сообщению в чате
//                sendMessage(inlineKeyboardShelterInformation2);
                sendMessage(projectKeyboardConverter.inlineKeyboard(chatId,
                        "Выберете, пожалуйста, вариант из предложенного меню!",
                        TheFirstKeyboardOfTheEntranceToTheShelterForCatsList.getList(), this));
            }
            break;
            case "/menu3": {
                sendMessage(chatId, " Извините, данный пункт в разработке !");
            }
            break;
        }
//        запускаем обработчик кнопок клавиатур
        HandlerForAllKeys.keyboardAndMenuHandler(text, chatId, this);

        System.out.println(" Выход обработался метод ==>  actionSelectorFromUpdate(String text, long chatId) ==>" + text);
    }

    /**
     * метод приветствие при запуске бота, составляет и возвращает строку приветствия
     */
    private String startStringReceived(String name) {
        String answer = " Мы рады Вас - " + name + ", видеть в нашем телеграмм боте ! \n "
                + "       Этот Бот является приютом для животных ! \n      Вы можете выбрать и усыновить животное, \n "
                + " либо стать волонтером. Что бы узнать подробнее \n        обратитесь к синей кнопке --> меню \n "
                + " и выберете тот пункт, который Вам интересен ). ";
        log.info("Replied to user " + name);
        return answer;
    }

    /**
     * метод проверяет пользователя пришел ли он впервые, или заходил ранее, наш пользователь ранее,
     * в телеграм бот, если он бывал ранее, то вызываем последнюю клавиатуру, которой он пользовался,
     * если он впервые, то приветствуем его сообщением из метода -> startStringReceived(String name)
     */
    private SendMessage userLogsInForTheFirstTime(String greetingText) {
        SendMessage sendMessage = new SendMessage();
//     Метод проверяет, был ли пользователь ранее в боте, используя
//     проверку наличия, ранее записанного при посещении бота chatId пользователя
//     в ->  Map<Long, List<String>> userAlreadyInteracted
        if (userAlreadyInteracted.containsKey(chatId)) {
//      далее выясняем на какой клавиатуре пользователь покинул бота
            List list = userAlreadyInteracted.get(chatId);
//           выводим клавиатуру в объект SendMessage
            return projectKeyboardConverter.inlineKeyboard(chatId,
                    "Выберете, пожалуйста, вариант из предложенного меню!",
                    list, this);
        } else {
//            выводим приветственное сообщение greetingText в объект SendMessage
            sendMessage.setText(greetingText);
            sendMessage.setChatId(chatId);
            return sendMessage;
        }
    }

    /**
     * метод выводит, простое, текстовое сообщения в телеграм бот
     */
    public void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    /**
     * метод, выводит различные виды клавиатур, отображает их в телеграм бот
     */
    public void sendMessage(SendMessage sendMessage) {
        try {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    /**
     * метод вызова и вывода фотографий в бот
     */
    public void sendPhoto(Long chatId) {
        try {
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
//          Указываем путь к файлу изображения
            File image = new File(getBotThePathToTheImageFile());
//          File image = new File("Telegram-Bot/src/main/resources/87295ec5.jpg");
            InputFile inputFile = new InputFile(image);
            sendPhotoRequest.setPhoto(inputFile);
            this.execute(sendPhotoRequest);
        } catch (NullPointerException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
}