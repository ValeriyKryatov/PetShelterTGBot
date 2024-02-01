package PetShelterTGBot.service;

import PetShelterTGBot.model.Report;
import PetShelterTGBot.service.TheKeyboardButtonMenu.*;
import PetShelterTGBot.config.BotConfig;
import PetShelterTGBot.theEnumConstants.Animals;
import PetShelterTGBot.theEnumConstants.TransferOfKeyboards;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.*;
import java.util.List;

import static PetShelterTGBot.theEnumConstants.Constant.GREETINGS_AT_THE_SHELTER_INFO;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    /** Переменные разрешающие запись отчета о животном взятым с приюта */
    boolean enablingThe_processingAnimalDiet_method = false;
    boolean enablingThe_processingPhotosForReport_method = false;
    boolean enablingThe_processingWellBeingAndAddiction_method = false;
    Animals animalsFlag;

    /**   объект с параметрами нужный для заполнения отчета и отправки сообщений волонтерам */
    public Report report = new Report();

    /**   текстовые сообщения, которые запускают те или иные обработчики */
    String messageText;
    /**    данный Map, запоминает параметрами, нужными для заполнения отчета и отправки сообщений волонтерам  */
    final Map<Long, Report> variablesForReportingAndMessagesToVolunteers = new HashMap<>();

    /**    данный Map, запоминает chatId пользователя и клавиатуру, где он находится в текущий момент */
    final Map<Long, List<String>> userAlreadyInteracted = new HashMap<>();

    /**    данный Set, запоминает, какие клавиатуры, проходил пользователь и подставляет их в обработку кнопок */
    private final BotConfig botConfig;

    /** активируем конвертор клавиатуры */
    ProjectKeyboardConverter projectKeyboardConverter;

    public TelegramBot(BotConfig botConfig,
                       ProjectKeyboardConverter projectKeyboardConverter
    ) {
        this.botConfig = botConfig;
        this.projectKeyboardConverter = projectKeyboardConverter;
//   создание кнопки Меню в левом углу командной строки бота
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
    public String getBotThePathToTheImageFile2() {
        return botConfig.getThePathToTheImageFile2();
    }

    public ProjectKeyboardConverter getProjectKeyboardConverter() {
        return projectKeyboardConverter;}

    /** самый главный метод, который принимает "сообщения" (объекты) присланные с телеграмм бота,
     выделяет нужные нам поля из данных (присланных) объектов и передает эти поля
     в (actionSelectorFromUpdate(String text, long chatId)) для дальнейшей обработки */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("  Вошли в метод ==> onUpdateReceived(Update update) ");

        if (update.hasMessage() && update.getMessage().hasText()) {
            report.setChatId(update.getMessage().getChatId());
            report.setNameUser(update.getMessage().getChat().getFirstName());
            messageText = update.getMessage().getText();
//              загрузка в отчет диеты питомца
            if (enablingThe_processingAnimalDiet_method){
                report.setAnimalDiet(messageText);
                enablingThe_processingAnimalDiet_method = false;
                messageText = "/well-being and addiction" + report.getAnimalsFlag().getTitle();
            }
//              загрузка в отчет самочувствия и привыкания к новому месту изменение привычек
            if (enablingThe_processingWellBeingAndAddiction_method){
                report.setWellBeingAndAddiction(messageText);

//                создать переменные о времени и заполнить их в userState
                Date date = new Date();

                variablesForReportingAndMessagesToVolunteers.put(report.getChatId(),report);

//               здесь должен (Написать КОД) вызваться метод записи, параметров отчета о питомце в базу данных

                enablingThe_processingWellBeingAndAddiction_method = false;
                messageText = "/come back" + report.getAnimalsFlag().getTitle();
            }

            actionSelectorFromUpdate(messageText, report.getChatId());
            System.out.println("  Определили имя пользователя бота в update.getMessage().hasText() ==> " + report.getNameUser());
            System.out.println("  Определили chatId для бота update.getMessage().getChatId()  ==> " + report.getChatId());
            System.out.println("  Этот текст, пришел от бота update.getMessage().getText() ==> " + messageText);
        }
        else if (update.hasCallbackQuery()) {
            messageText = update.getCallbackQuery().getData();
            System.out.println("  Этот текст, пришел от бота в update.getCallbackQuery().getData() ==> " + messageText);
            actionSelectorFromUpdate(messageText,report.getChatId());
            System.out.println("  Выход обработался метод ==> onUpdateReceived(Update update) ==>  " + messageText );
        }
//        загрузка фото для отчета
        else if (update.hasMessage() && enablingThe_processingPhotosForReport_method) {
//            String tempText = messageText.split("#")[1];
            if (update.getMessage().hasPhoto()) {
                processingPhotosForReport(update, report.getAnimalsFlag());
            } else {
                messageText = "/animal not photo" + report.getAnimalsFlag().getTitle();
            }
            enablingThe_processingPhotosForReport_method = false;
            actionSelectorFromUpdate(messageText, report.getChatId());
        }
    }

    /**    метод обработки сообщений (полей) из главного Меню, от метода -> onUpdateReceived(Update update),
     *     выбирает (в зависимости от пункта) и выводит в бот, следующую клавиатуру   */
    public void actionSelectorFromUpdate(String text, long chatId){
        System.out.println(" Вошли в метод ==>  actionSelectorFromUpdate ==>" + text);
        switch (text) {
            case "/start":
                System.out.println(" Вошли ==>   case \"/start\" в метод actionSelectorFromUpdate ==>" + text);
//              Проверка, был ли пользователь в нашем ранее боте, если пользователь впервые - то приветствие,
//                если нет то клавиатура, которую ранее покинул, входящий сейчас пользователь
                sendMessage(userLogsInForTheFirstTime(startStringReceived(report.getNameUser())));
                break;
            case "/menu1": {
//             Вызов, (отображение) клавиатуры привязанной к сообщению в чате
                sendMessage(projectKeyboardConverter.inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                        TheFirstKeyboardOfTheEntranceShelterForAnimal.getList(Animals.CAT)));
            }
            break;
            case "/menu2": {
//              Вызов, (отображение) клавиатуры привязанной к сообщению в чате
//                sendMessage(inlineKeyboardShelterInformation2);
                sendMessage(projectKeyboardConverter.inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                        TheFirstKeyboardOfTheEntranceShelterForAnimal.getList(Animals.DOG)));
            }
            break;
            case "/menu3": {
                sendMessage(chatId, " Извините, данный пункт в разработке !");
            }
            break;
        }
//        запускаем обработчик кнопок клавиатур
        HandlerForAllKeys.keyboardAndMenuHandler(text, chatId,this);

        System.out.println(" Выход обработался метод ==>  actionSelectorFromUpdate(String text, long chatId) ==>" + text);
    }

    /**   метод приветствие при запуске бота, составляет и возвращает строку приветствия */
    private String startStringReceived(String name) {
        String answer = " Мы рады Вас - " + name + ", видеть в нашем телеграмм боте ! \n "
                + "       Этот Бот является приютом для животных ! \n      Вы можете выбрать и усыновить животное, \n "
                + " либо стать волонтером. Что бы узнать подробнее \n        обратитесь к синей кнопке --> меню \n "
                + " и выберете тот пункт, который Вам интересен ). ";
        log.info("Replied to user " + name);
        return answer;
    }

    /** метод проверяет пользователя пришел ли он впервые, или заходил ранее, наш пользователь ранее,
     в телеграм бот, если он бывал ранее, то вызываем последнюю клавиатуру, которой он пользовался,
     если он впервые, то приветствуем его сообщением из метода -> startStringReceived(String name) */
    private SendMessage userLogsInForTheFirstTime (String greetingText) {
        SendMessage sendMessage = new SendMessage();
//     Метод проверяет, был ли пользователь ранее в боте, используя
//     проверку наличия, ранее записанного при посещении бота chatId пользователя
//     в ->  Map<Long, List<String>> userAlreadyInteracted
        if (userAlreadyInteracted.containsKey(report.getChatId())){
//      далее выясняем на какой клавиатуре пользователь покинул бота
            List list = userAlreadyInteracted.get(report.getChatId());
//           выводим клавиатуру в объект SendMessage
            return projectKeyboardConverter.inLineKeyboard(report.getChatId(),
                    "Выберете, пожалуйста, вариант из предложенного меню!",
                    list).getSendMessage();
        } else {
//            выводим приветственное сообщение greetingText в объект SendMessage
            sendMessage.setText(greetingText);
            sendMessage.setChatId(report.getChatId());
            return sendMessage;
        }
    }

    /**    метод выводит, простое, текстовое сообщения в телеграм бот */
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

    /**    метод, выводит различные виды клавиатур, отображает их в телеграм бот */

    public void sendMessage (SendMessage message){
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    public void sendMessage (TransferOfKeyboards transferOfKeyboards){
        SendMessage message = transferOfKeyboards.getSendMessage();
        this.userAlreadyInteracted.put(Long.valueOf(transferOfKeyboards.getSendMessage().getChatId()),transferOfKeyboards.getList());
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    /**    метод вызова и вывода фотографий карты (картинка) с адресом в бот */
    public void sendPhoto(Long chatId, Animals en) {
        try {
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
//          Указываем путь к файлу изображения подключаем либо с application.properties либо напрямую
            File image = null;
            if (Animals.CAT == en){
             image = new File(getBotThePathToTheImageFile());
//          File image = new File(src/main/resources/catShelter.jpg");
            } else if (Animals.DOG == en) {
             image = new File(getBotThePathToTheImageFile2());
//          File image = new File(src/main/resources/dogShelter.jpg");
            }
            InputFile inputFile = new InputFile(image);
            sendPhotoRequest.setPhoto(inputFile);
            this.execute(sendPhotoRequest);
        } catch (NullPointerException | TelegramApiException e) {
            e.printStackTrace();
        }
    }
    /**    обработка присланного фото для заполнения отчета от усыновителя */
    public void processingPhotosForReport(Update update, Animals animals) {
        int size = update.getMessage().getPhoto().size();
        report.setPhotoAnimal(update.getMessage().getPhoto().get(size - 1));
        System.out.println(" вошли в метод  ==>   public void processingPhotosForReport(Update update, Animals animals) " + size);
        messageText = "/animal diet" + animals.getTitle();
    }
}