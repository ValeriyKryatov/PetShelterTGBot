package PetShelterTGBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * это класс который создает меню в углу командной строки
 **/
@Slf4j
@Service
public class MainMenu {
    public static void mainMenuButton (TelegramBot bot) {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Приветствие"));
        listOfCommands.add(new BotCommand("/menu1", "Приют для кошек"));
        listOfCommands.add(new BotCommand("/menu2", "Приют для собак"));
        listOfCommands.add(new BotCommand("/menu3", ".... в разработке "));
        try {
            bot.execute(new SetMyCommands(listOfCommands,
                    new BotCommandScopeDefault(),
                    null));
        } catch (
                TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
            e.printStackTrace();
        }
    }
}