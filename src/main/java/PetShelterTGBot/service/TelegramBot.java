package PetShelterTGBot.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import PetShelterTGBot.model.Report;
import PetShelterTGBot.config.BotConfig;
import PetShelterTGBot.theEnumConstants.Animals;
import PetShelterTGBot.service.TheKeyboardButtonMenu.*;
import PetShelterTGBot.theEnumConstants.TransferOfKeyboards;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.*;
import java.util.List;

import static PetShelterTGBot.theEnumConstants.Constant.GREETINGS_AT_THE_SHELTER_INFO;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Переменные разрешающие запись отчета о животном взятым с приюта
     */
    boolean enablingThe_processingAnimalDiet_method = false;
    boolean enablingThe_processingPhotosForReport_method = false;
    boolean enablingThe_processingWellBeingAndAddiction_method = false;
    /**
     * этот энам  животных применяется для правильного вызова клавиатур и обработки
     */
    Animals animalsFlag;
    /**
     * текстовые сообщения, которые запускают те или иные обработчики
     */
    String messageText;
    /**
     * данный Map, запоминает параметрами, нужными для заполнения отчета и отправки сообщений волонтерам
     */
    final Map<Long, Report> variablesForReportingAndMessagesToVolunteers = new HashMap<>();

    /**
     * данный Map, запоминает chatId пользователя и клавиатуру, где он находится в текущий момент
     */
    final Map<Long, List<String>> userAlreadyInteracted = new HashMap<>();

    private final BotConfig botConfig;
    // ReportService todo:

    /**
     * активируем конвертор клавиатуры
     */
    @Getter
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

    private String getBotThePathToTheImageFileCat() {
        return botConfig.getThePathToTheImageFileCat();
    }

    private String getBotThePathToTheImageFileDog() {
        return botConfig.getThePathToTheImageFileDog();
    }

//    private String getWayToStoreTemporaryPhotos() {
//        return botConfig.getWayToStoreTemporaryPhotos();
//    }

    /**
     * самый главный метод, который принимает "сообщения" (объекты) присланные с телеграмм бота,
     * выделяет нужные нам поля из данных (присланных) объектов и передает эти поля
     * в (actionSelectorFromUpdate(String text, long chatId)) для дальнейшей обработки
     * @param update
     */
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("  Вошли в метод ==> onUpdateReceived(Update update) ");
        long chatId = fetchChatId(update);
        Report report = getOrCreateReport(update, chatId);

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                messageText = update.getMessage().getText();
//              загрузка в отчет диеты питомца
                if (enablingThe_processingAnimalDiet_method) {
                    report.setAnimalDiet(messageText);
                    enablingThe_processingAnimalDiet_method = false;
                    messageText = "/well-being and addiction" + report.getAnimalsFlag().getTitle();
                }
//              загрузка в отчет самочувствия и привыкания к новому месту изменение привычек
                if (enablingThe_processingWellBeingAndAddiction_method) {
                    report.setWellBeingAndAddiction(messageText);
                    enablingThe_processingWellBeingAndAddiction_method = false;
                    Report reportToSave = variablesForReportingAndMessagesToVolunteers.remove(chatId);
                    // todo:
                    //      reportService.save(reportToSave);
                    messageText = "/come back" + report.getAnimalsFlag().getTitle();
                }

                actionSelectorFromUpdate(messageText, report.getChatId(), report);
                System.out.println("  Определили имя пользователя бота в update.getMessage().hasText() ==> " + report.getNameUser());
                System.out.println("  Определили chatId для бота update.getMessage().getChatId()  ==> " + report.getChatId());
                System.out.println("  Этот текст, пришел от бота update.getMessage().getText() ==> " + messageText);
            } else {
                if (enablingThe_processingPhotosForReport_method) { // загрузка фото для отчета
                    //            String tempText = messageText.split("#")[1];
                    if (update.getMessage().hasPhoto()) {
                        processingPhotosForReport(update, report);
                        messageText = "/animal diet" + report.getAnimalsFlag().getTitle();
                    } else {
                        messageText = "/animal not photo" + report.getAnimalsFlag().getTitle();
                    }
                    enablingThe_processingPhotosForReport_method = false;
                    actionSelectorFromUpdate(messageText, report.getChatId(), report);
                }
            }
        } else if (update.hasCallbackQuery()) {
            messageText = update.getCallbackQuery().getData();
            System.out.println("  Этот текст, пришел от бота в update.getCallbackQuery().getData() ==> " + messageText);
            actionSelectorFromUpdate(messageText, chatId, report);
            System.out.println("  Выход обработался метод ==> onUpdateReceived(Update update) ==>  " + messageText);
        }
    }

    private Report getOrCreateReport(Update update, long chatId) {
        Report report = variablesForReportingAndMessagesToVolunteers.get(chatId);
        if (report == null) {
            Report reportByUser = new Report();
            reportByUser.setChatId(chatId);
            reportByUser.setNameUser(update.getMessage().getChat().getFirstName());
            reportByUser.setDateReport(new Date()); // создать переменные о времени и заполнить их в userState
            // reportByUser.setDateEndOfProbation(); todo: установить дату окончания испытательного срока
            // проверить срок отчета, но тогда нужно учеcть, когда пользователь действительно сдал отчет
            // потому что сейчас, отчет создается при первом сообщении от пользователя, независимо какое это было сообщение
            variablesForReportingAndMessagesToVolunteers.put(chatId, reportByUser);
            return reportByUser;
        }
        return report;
    }

    /**
     * Возвращает chatId из update
     *
     * @param update
     * @return long
     */
    private static long fetchChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        throw new IllegalStateException("Cannot fetch chat id");
    }

    /**
     * метод обработки сообщений (полей) из главного Меню, от метода -> onUpdateReceived(Update update),
     * выбирает (в зависимости от пункта) и выводит в бот, следующую клавиатуру
     * @param text
     * @param chatId
     * @param report
     */
    public void actionSelectorFromUpdate(String text, long chatId, Report report) {
        System.out.println(" Вошли в метод ==>  actionSelectorFromUpdate ==>" + text);
        switch (text) {
            case "/start":
                System.out.println(" Вошли ==>   case \"/start\" в метод actionSelectorFromUpdate ==>" + text);
//              Проверка, был ли пользователь в нашем ранее боте, если пользователь впервые - то приветствие,
//                если нет то клавиатура, которую ранее покинул, входящий сейчас пользователь
                sendMessage(userLogsInForTheFirstTime(startStringReceived(report.getNameUser()), chatId));
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
        HandlerForAllKeys.keyboardAndMenuHandler(text, chatId, this, report);

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
    private SendMessage userLogsInForTheFirstTime(String greetingText, long chatId) {
        SendMessage sendMessage = new SendMessage();
//     Метод проверяет, был ли пользователь ранее в боте, используя
//     проверку наличия, ранее записанного при посещении бота chatId пользователя
//     в ->  Map<Long, List<String>> userAlreadyInteracted
        if (userAlreadyInteracted.containsKey(chatId)) {
//      далее выясняем на какой клавиатуре пользователь покинул бота
            List list = userAlreadyInteracted.get(chatId);
//           выводим клавиатуру в объект SendMessage
            return projectKeyboardConverter.inLineKeyboard(chatId,
                    "Выберете, пожалуйста, вариант из предложенного меню!",
                    list).getSendMessage();
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

    public void sendMessage(SendMessage message) {
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    public void sendMessage(TransferOfKeyboards transferOfKeyboards) {
        SendMessage message = transferOfKeyboards.getSendMessage();
        this.userAlreadyInteracted.put(Long.valueOf(transferOfKeyboards.getSendMessage().getChatId()), transferOfKeyboards.getList());
        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    /**
     * метод вызова и вывода фотографий карты (картинка) с адресом в бот
     */
    public void sendPhoto(Long chatId, Animals en) {
        try {
            SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(chatId);
//          Указываем путь к файлу изображения подключаем либо с application.properties либо напрямую
            File image = null;
            if (Animals.CAT == en) {
                image = new File(this.getBotThePathToTheImageFileCat());
//          File image = new File("src/main/resources/catShelter.jpg");
            } else if (Animals.DOG == en) {
                image = new File(this.getBotThePathToTheImageFileDog());
//          File image = new File("src/main/resources/dogShelter.jpg");
            }
            InputFile inputFile = new InputFile(image);
            sendPhotoRequest.setPhoto(inputFile);
            this.execute(sendPhotoRequest);
        } catch (NullPointerException | TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * обработка присланного фото для заполнения отчета от усыновителя
     */
    @Transactional
    public void processingPhotosForReport(Update update, Report report) {
        try {
            var photos = update.getMessage().getPhoto();
            PhotoSize photo = photos.get(photos.size() - 1);
            GetFile getFile = new GetFile(photo.getFileId());
            org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
            var bytes = IOUtils.toByteArray(downloadFileAsStream(file));
            report.setPhotoAnimal(bytes);


//*************************************************************************************
// код, который сохраняет фото в виде файла, а затем переводит этот файл в массив байт
//
// этот участок кода сохраняет фото в виде файла и записывает на в папку
//            String filePathPhoto = this.getWayToStoreTemporaryPhotos();
//            org.telegram.telegrambots.meta.api.objects.File file = null;
//            PhotoSize photo = update.getMessage().getPhoto().get(2);
//            GetFile getFile = new GetFile(photo.getFileId());
//
//            file = this.execute(getFile);
//            this.downloadFile(file, new java.io.File(filePathPhoto));
//
// этот участок кода преобразует файл с диска в байтовый массив
//            File fileN = new File(filePathPhoto);
//            byte[] bytes = new byte[(int) fileN.length()];
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(fileN);
//                fis.read(bytes);
//            } finally {
//                if (fis != null) {
//                    fis.close();
//                }
//            }
//
// ************************************************************************************
//  это более старый код преобразования файла в массив байтов, он не используется в выше написанном коде
//            file.setFilePath(filePathPhoto);
//            byte b;
//            byte[] byteArr;
//            FileInputStream fis = new FileInputStream(filePathPhoto);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            while ((b = (byte) fis.read()) != -1) {
//                baos.write(b);
//            }
//            byteArr = baos.toByteArray();
//            fis.close();
//            baos.close();
// ************************************************************************************
//  этот участок кода записывает в репорт массив байт и удаляет файл с диска
//            report.setPhotoAnimal(bytes);
//            Files.delete(Path.of(filePathPhoto));
// ************************************************************************************


        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
