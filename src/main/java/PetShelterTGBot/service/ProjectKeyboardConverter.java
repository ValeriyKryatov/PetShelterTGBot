package PetShelterTGBot.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class ProjectKeyboardConverter  {
//     отображение кнопок клавиатуры привязанной к сообщению в телеграм боте

    public SendMessage inLineKeyboard(long chat_id, String incomingText, List<String> list, TelegramBot bot) {

        bot.userAlreadyInteracted.put(chat_id,list);
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(incomingText);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        for (int i = 0; i < list.size(); ){
        List<InlineKeyboardButton> rowInlineT = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(list.get(i));
        inlineKeyboardButton.setCallbackData(list.get(++i));
        rowInlineT.add(inlineKeyboardButton);
        rowsInline.add(rowInlineT);
        i++;
        }
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        return message;
    }

}
