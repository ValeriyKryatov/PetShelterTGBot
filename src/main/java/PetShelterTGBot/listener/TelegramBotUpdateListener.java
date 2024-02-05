package PetShelterTGBot.listener;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TelegramBotUpdateListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdateListener.class);

    public TelegramBotUpdateListener() {
    }
}