package PetShelterTGBot.controller;

import PetShelterTGBot.model.MessagesForVolunteers;
import PetShelterTGBot.service.MessagesForVolunteersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер сообщений для волонтеров.
 */
@RestController
@RequestMapping("/pet_shelter/message_for_volunteer")
@Tag(name = "API по работе с сообщениями для волонтеров",
        description = "CRUD-операции для работы с сообщениями для волонтеров")
public class MessagesForVolunteersController {

    private final MessagesForVolunteersService messagesForVolunteersService;

    public MessagesForVolunteersController(MessagesForVolunteersService messagesForVolunteersService) {
        this.messagesForVolunteersService = messagesForVolunteersService;
    }

    @GetMapping
    @Operation(
            summary = "Список всех сообщений для волонтеров"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список сообщений для волонтеров"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<MessagesForVolunteers>> getAllMessagesForVolunteers() {
        return ResponseEntity.ok(messagesForVolunteersService.getAllMessagesForVolunteers());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление сообщения для волонтера по идентификатору",
            description = "Нужно написать id сообщения, которое нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить сообщение для волонтера"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<MessagesForVolunteers> deleteMessagesForVolunteers(@PathVariable Long id) {
        messagesForVolunteersService.deleteMessagesForVolunteers(id);
        return ResponseEntity.ok().build();
    }
}