package PetShelterTGBot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MainMenu {
    public static void mainMenuButton (TelegramBot bot) {
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/menu1", "Приют для кошек"));
        listofCommands.add(new BotCommand("/menu2", "Приют для собак"));


        try {
            bot.execute(new SetMyCommands(listofCommands,
                    new BotCommandScopeDefault(),
                    null));
        } catch (
                TelegramApiException e) {
            log.error("Error setting bot's command list: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

