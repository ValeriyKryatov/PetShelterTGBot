package PetShelterTGBot.service;

import PetShelterTGBot.model.MessagesForVolunteers;
import PetShelterTGBot.model.VisitToShelter;
import PetShelterTGBot.repository.MessagesForVolunteersRepository;
import PetShelterTGBot.repository.ReportRepository;
import PetShelterTGBot.repository.VisitToShelterRepository;
import lombok.Getter;
import lombok.Synchronized;
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
import java.util.Date;
import java.util.regex.Pattern;

import static PetShelterTGBot.theEnumConstants.Constant.GREETINGS_AT_THE_SHELTER_INFO;

@Slf4j
@Service
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Переменные (флаги) разрешающие запись отчета о животном взятым с приюта
     */
    boolean enablingThe_processingAnimalDiet_method = false;
    boolean enablingThe_processingPhotosForReport_method = false;
    boolean enablingThe_processingWellBeingAndAddiction_method = false;
    boolean enablingMessageMethodProcessingForVolunteers = false;
    boolean enablingProcessingOfMethodsForReportingVisitToShelter = false;

    /**
     * данный,  Энам животных, применяется для правильного вызова клавиатур и обработки методов
     * в зависимости с каким видом животного мы имеем дело
     */
    Animals animalsFlag;
    /**
     * текстовые сообщения, которые запускают те или иные обработчики (методы)
     * на них построена вся логика обработки и вызовов меню
     */
    String messageText;
    /**
     * данный Map, запоминается параметрами, нужными для отправления отчета
     * (о взятом животном домой) пользователями для волонтеров,
     * ключом является chatId, а Report сущность в которую вкладываются поля отчета,
     * что бы каждому пользователю chatId, соответсвовали свои поля отчета,
     * при одновременном вводе отчета сразу несколькими пользователями в бот,
     * будет вызываться из Мары заполняемый отчет Report принадлежащий  определенному
     * пользователя по chatId и записываться в него свое (поле) значение
     */
    private final Map<Long, Report> variablesToReportToVolunteers = new HashMap<>();

    /**
     * данный Map, запоминает chatId пользователя и клавиатуру, где он находится в текущий момент
     */
    private final Map<Long, List<String>> userAlreadyInteracted = new HashMap<>();

    private final BotConfig botConfig;
    // ReportService todo:

    /**
     * активируем конвертор клавиатуры
     */
    @Getter
    private final ProjectKeyboardConverter projectKeyboardConverter;
    /**
     * Подключаем репозиторий для Report
     */
    private final ReportRepository reportRepository;
    /**
     * Подключаем репозиторий для MessagesForVolunteersRepository
     */
    private final MessagesForVolunteersRepository messagesForVolunteersRepository;
    /**
     * Подключаем репозиторий для VisitToShelterRepository
     */
    private final VisitToShelterRepository visitToShelterRepository;

    public TelegramBot(BotConfig botConfig,
                       ReportRepository reportRepository,
                       MessagesForVolunteersRepository messagesForVolunteersRepository,
                       VisitToShelterRepository visitToShelterRepository,
                       ProjectKeyboardConverter projectKeyboardConverter
    ) {
        this.botConfig = botConfig;
        this.reportRepository = reportRepository;
        this.messagesForVolunteersRepository = messagesForVolunteersRepository;
        this.visitToShelterRepository = visitToShelterRepository;
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

    private long getProbationaryPeriod() {
        return botConfig.getProbationaryPeriod();
    }

    /**
     * самый главный метод, который принимает "сообщения" (объекты) присланные с телеграмм бота,
     * выделяет нужные нам поля из данных (присланных) объектов и передает эти поля
     * в (actionSelectorFromUpdate(String text, long chatId)) для дальнейшей обработки
     *
     * @param update
     */
    @Synchronized
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("  Вошли в метод ==> onUpdateReceived(Update update) ");
        long chatId = fetchChatId(update);
        Report report = getOrCreateReport(update, chatId);

        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                messageText = update.getMessage().getText();
// загрузка сообщений о визите
                if (enablingProcessingOfMethodsForReportingVisitToShelter) {
                    if (processingMessagesForVisitShelter(update)) {
                        sendMessage(chatId, " Сообщение отправлено. Вам перезвонят ! ");
                        enablingProcessingOfMethodsForReportingVisitToShelter = false;
                        messageText = "/visit to the shelter treatment" + animalsFlag.getTitle();
                    } else {
                        sendMessage(chatId, " Сообщение не отправлено. Возникла ошибка при заполнении ! ");
                        enablingProcessingOfMethodsForReportingVisitToShelter = false;
                        messageText = "/come back" + animalsFlag.getTitle();
                    }
                }
// загрузка сообщения от пользователя для волонтера
                if (enablingMessageMethodProcessingForVolunteers) {
                    if(processingMessagesForVolunteers(update)){
                        sendMessage(chatId, " Сообщение отправлено волонтеру ! ");
                        enablingMessageMethodProcessingForVolunteers = false;
                        messageText = "/come back" + animalsFlag.getTitle();
                        System.out.println("processingMessagesForVolunteers(update) ======> " + messageText
                                + " Сообщение отправлено волонтеру ! ");
                    } else {
                        sendMessage(chatId, " Сообщение не отправлено. Возникла ошибка при заполнении ! ");
                        enablingMessageMethodProcessingForVolunteers = false;
                        messageText = "/come back" + animalsFlag.getTitle();
                        System.out.println("processingMessagesForVolunteers(update) ======> " + messageText
                                + " Сообщение не отправлено. Возникла ошибка при заполнении ! ");
                    }
                }
// загрузка в отчет диеты питомца, второе действие - промежуточное при составлении отчета
                if (enablingThe_processingAnimalDiet_method) {
                    report.setAnimalDiet(messageText);
                    enablingThe_processingAnimalDiet_method = false;
                    messageText = "/well-being and addiction" + report.getAnimalsFlag().getTitle();
                }
// загрузка в отчет самочувствия и привыкания к новому месту изменение привычек, третье действие - последнее при составлении отчета
                if (enablingThe_processingWellBeingAndAddiction_method) {
                    report.setWellBeingAndAddiction(messageText);
                    Date dateTemp = report.getDateReport();
                    long dataTempMillisec = dateTemp.getTime();
                    System.out.println(" время dataTemp в миллисекундах  ==> " + dataTempMillisec);
                    dataTempMillisec += getProbationaryPeriod();
                    Date dateEndOfProbation = new Date(dataTempMillisec);
                    report.setDateEndOfProbation(dateEndOfProbation);
                    enablingThe_processingWellBeingAndAddiction_method = false;

//                    Report reportToSave = variablesToReportToVolunteers.remove(chatId);

                    if ((report.getChatId() != null)
                            && (report.getAnimalDiet() != null)
                            && (report.getDateReport() != null)
                            && (report.getDateEndOfProbation() != null)
                            && (report.getWellBeingAndAddiction() != null)
                            && (report.getPhotoAnimal() != null)
                    ) {
//                        скоп с кодом если база данных не нумерует id самостоятельно
                        {
//                            long idTemp;
//                            if ( reportRepository.count() == 0){ idTemp = 0;
//                        //          вытаскиваем последнюю запись из базы данных, далее из неё id
//                            } else { idTemp = reportRepository.findFirstByOrderByIdDesc().getId();}
//                        report.setId(++idTemp);
                        }

                        // статус отчета 1 - не проверен, 2 - напоминание направлено, 3 - испытательный срок закрыт
                        report.setStatusReport(1);
                        // записываем отчет в базу данных
                        reportRepository.save(report);
                        sendMessage(chatId, " Отчет составлен и отправлен на проверку ! ");
                    } else {
                        sendMessage(chatId, " Отчет имеет ошибку ! ");
                    }
                    messageText = "/come back" + report.getAnimalsFlag().getTitle();
                }

                actionSelectorFromUpdate(messageText, report.getChatId(), report);
                System.out.println("  Определили имя пользователя бота в update.getMessage().hasText() ==> " + report.getNameUser());
                System.out.println("  Определили chatId для бота update.getMessage().getChatId()  ==> " + report.getChatId());
                System.out.println("  Этот текст, пришел от бота update.getMessage().getText() ==> " + messageText);
            } else {
// загрузка в отчет фотографии животного, первое действие - начало составления отчета
                if (enablingThe_processingPhotosForReport_method) {
                    if (update.getMessage().hasPhoto()) {
                        processingPhotosForReport(update, report);
                        messageText = "/animal diet" + report.getAnimalsFlag().getTitle();
                    } else {
                        messageText = "/animal not photo" + report.getAnimalsFlag().getTitle();
                    }
                    report.setDateReport(new Date());
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

    /**
     * метод, который принимает сообщение от бота update и chatId пользователя
     * и возвращает нам report этого пользователя,
     * если пользователь вошел впервые то создается report, частично заполняется
     * (полями) частями отчета и возвращает report
     *
     * @param update
     * @param chatId
     * @return
     */
    private Report getOrCreateReport(Update update, long chatId) {
        Report report = variablesToReportToVolunteers.get(chatId);
        if (report == null) {
            Report reportByUser = new Report();
            reportByUser.setChatId(chatId);
            reportByUser.setNameUser(update.getMessage().getChat().getFirstName());
            variablesToReportToVolunteers.put(chatId, reportByUser);
            return reportByUser;
        }
        return report;
    }

    /**
     * Возвращает из сообщения телеграмма update --> chatId пользователя
     *
     * @param update
     * @return long
     */
    private static long fetchChatId(Update update) {
        if (update.hasMessage()) {
            System.out.println("метод => private static long fetchChatId(Update update) \n" +
                    "возвращает из update.getMessage().getChatId() "
                    + update.getMessage().getChatId());
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            System.out.println("метод => private static long fetchChatId(Update update) \n" +
                    "возвращает из update.getCallbackQuery().getMessage().getChatId()"
                    + update.getCallbackQuery().getMessage().getChatId());
            return update.getCallbackQuery().getMessage().getChatId();
        }
        System.out.println(" Внимание проблема с БОТОМ !!!");
        throw new IllegalStateException("Cannot fetch chat id");
    }

    /**
     * метод обработки сообщений (полей) из главного Меню, от метода -> onUpdateReceived(Update update),
     * выбирает (в зависимости от пункта) и выводит в бот, следующую клавиатуру
     *
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
                sendMessage(projectKeyboardConverter.inLineKeyboard(chatId, GREETINGS_AT_THE_SHELTER_INFO,
                        TheFirstKeyboardOfTheEntranceShelterForAnimal.getList(Animals.DOG)));
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
     * данный метод выводит, простое, текстовое сообщения в телеграм бот
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
     * данный метод, выводит различные виды клавиатур, отображает их в телеграм бот
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
     *
     * @param chatId
     * @param en
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
     *
     * @param update
     * @param report
     */
    @Transactional
    public void processingPhotosForReport(Update update, Report report) {
        try {
            var photos = update.getMessage().getPhoto();
            PhotoSize photo = photos.get(photos.size() - 1);
            GetFile getFile = new GetFile(photo.getFileId());
            org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
            byte[] bytes = IOUtils.toByteArray(downloadFileAsStream(file));
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

    /**
     * обработка сообщения для волонтера
     *
     * @param update
     */
    @Transactional
    public boolean processingMessagesForVolunteers(Update update) {
        Message message = update.getMessage();
        if (message.hasText()) {
            long chatId = fetchChatId(update);
            String nameUser = update.getMessage().getChat().getFirstName();
            String messageText = update.getMessage().getText();
            MessagesForVolunteers messagesForVolunteers = new MessagesForVolunteers();
            messagesForVolunteers.setChatId(chatId);
            messagesForVolunteers.setNameUser(nameUser);
            messagesForVolunteers.setDate(new Date());
            messagesForVolunteers.setText(messageText);
            messagesForVolunteersRepository.save(messagesForVolunteers);
        } else { return false;}
        return true;
    }

    /**
     * обработка сообщения о посещении приюта
     *
     * @param update
     * @return boolean
     */
    @Transactional
    public boolean processingMessagesForVisitShelter(Update update) {
        boolean returnFlag = false;
        Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{11})(\\s)([\\W+]+)");
        Message message = update.getMessage();
        if (message.hasText()) {
            String messageText = update.getMessage().getText();
            var matcher = PATTERN.matcher(messageText);
            if (matcher.matches()) {
                String s = matcher.group(1);
                var telephone = Long.parseLong(s);
                String messageT = matcher.group(3);
                long chatId = fetchChatId(update);
                String nameUser = update.getMessage().getChat().getFirstName();
                VisitToShelter visitToShelter = new VisitToShelter();
                visitToShelter.setChatId(chatId);
                visitToShelter.setNameUser(nameUser);
                visitToShelter.setDate(new Date());
                visitToShelter.setText(messageT);
                visitToShelter.setTelephone(telephone);
                visitToShelterRepository.save(visitToShelter);
                returnFlag = true;
            } else {  returnFlag = false;  }
        }
        return returnFlag;
    }
}