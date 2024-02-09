package PetShelterTGBot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {
    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.hetPathToTheImageFileCat}")
    private String thePathToTheImageFileCat;
    @Value("${bot.hetPathToTheImageFileDog}")
    private String thePathToTheImageFileDog;
    @Value("${bot.specifiedTime_30_daysInMilliseconds}")
    private Long probationaryPeriod;
    @Value("${bot.specifiedTime_1_daysInMilliseconds}")
    private Long waitingTimeForReportDays;
}