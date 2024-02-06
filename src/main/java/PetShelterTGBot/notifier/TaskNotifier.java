package PetShelterTGBot.notifier;

import PetShelterTGBot.config.BotConfig;
import PetShelterTGBot.repository.ReportRepository;
import PetShelterTGBot.service.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@EnableScheduling
public class TaskNotifier {
    private final static Logger logger = LoggerFactory.getLogger(TaskNotifier.class);
    private final BotConfig botConfig;
    private final TelegramBot bot;
    private final ReportRepository reportRepository;

    public TaskNotifier(BotConfig botConfig,
                        TelegramBot bot,
                        ReportRepository reportRepository
    ) {
        this.botConfig = botConfig;
        this.bot = bot;
        this.reportRepository = reportRepository;
    }

    public Long getWaitingTimeForReportDays() {
        return botConfig.getWaitingTimeForReportDays();
    }

    //@Scheduled(cron = "0 0/1 * * * *")
    @Scheduled(timeUnit = TimeUnit.HOURS, fixedDelay = 2)
    public void notifyTask1() {
        System.out.println(" Метода -->  @Scheduled(timeUnit = TimeUnit.HOURS, fixedDelay = 2) \n " +
                "                  public void notifyTask1()  ЗАПУСТИЛСЯ !!!!! ");
        Date currentDate = new Date();
        reportRepository.findAllByStatusReport(1)
                .forEach(report -> {
                    var dateReport = report.getDateReport();
                    var chatId = report.getChatId();
                    if (currentDate.getTime() - dateReport.getTime() > getWaitingTimeForReportDays()) {
                        bot.sendMessage(chatId, " От Вас не поступил отчета в течении суток ! \n " +
                                " Пожалуйста пришлите его как можно быстрее ! ");
                        report.setStatusReport(2);
                        reportRepository.save(report);
                        System.out.println(chatId + "Отправлено уведомление об просроченном отчете !");
                        logger.info(chatId + "The notification about the overdue report has been sent !");
                    }
                });
    }

    @Scheduled(timeUnit = TimeUnit.DAYS, fixedDelay = 1)
    public void notifyTask2() {
        System.out.println(" Метода -->  @Scheduled(timeUnit = TimeUnit.DAYS, fixedDelay = 1) \n " +
                "                  public void notifyTask2()  ЗАПУСТИЛСЯ !!!!! ");
        Date currentDate = new Date();
        reportRepository.findAllByStatusReport(2)
                .forEach(report -> {
                    var chatId = report.getChatId();
                    if (report.getDateEndOfProbation().getTime() > currentDate.getTime()) {
                        bot.sendMessage(chatId, " У Вас закончился испытательны срок, по отчетам ! ");
                        report.setStatusReport(3);
                        reportRepository.save(report);
                        logger.info(chatId + "Notification of the end of the probationary period has been sent!");
                    }
                });
    }
}