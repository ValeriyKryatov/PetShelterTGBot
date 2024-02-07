package PetShelterTGBot.controller;

import PetShelterTGBot.model.VisitToShelter;
import PetShelterTGBot.service.VisitToShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер сообщений о посещении приюта.
 */
@RestController
@RequestMapping("/pet_shelter/visit_to_shelter")
@Tag(name = "API по работе с сообщениями о посещении приюта",
        description = "CRUD-операции для работы с сообщениями о посещении приюта")
public class VisitToShelterController {

    private final VisitToShelterService visitToShelterService;

    public VisitToShelterController(VisitToShelterService visitToShelterService) {
        this.visitToShelterService = visitToShelterService;
    }

    @GetMapping
    @Operation(
            summary = "Список всех сообщений о посещении приюта"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список сообщений о посещении приюта"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<VisitToShelter>> getAllVisitToShelter() {
        return ResponseEntity.ok(visitToShelterService.getAllVisitToShelter());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление сообщений о посещении приюта по идентификатору",
            description = "Нужно написать id сообщения о посещении приюта, которое нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить сообщение о посещении приюта"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<VisitToShelter> deleteVisitToShelter(@PathVariable Long id) {
        visitToShelterService.deleteVisitToShelter(id);
        return ResponseEntity.ok().build();
    }
}