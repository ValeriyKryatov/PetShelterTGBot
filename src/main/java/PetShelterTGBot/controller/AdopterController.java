package PetShelterTGBot.controller;

import PetShelterTGBot.exception.NotFoundAdopterException;
import PetShelterTGBot.model.Adopter;
import PetShelterTGBot.service.AdopterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер усыновителя.
 */
@RestController
@RequestMapping("/pet_shelter/adopter")
@Tag(name = "API по работе с усыновителями",
        description = "CRUD-операции для работы с усыновителями")
public class AdopterController {

    private final AdopterService adopterService;

    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск усыновителя по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить усыновителя по id"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Adopter> getAdopterInfo(@PathVariable Long id) {
        Adopter adopter = adopterService.readAdopter(id);
        if (adopter == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(adopter);
    }

    @GetMapping
    @Operation(
            summary = "Список всех усыновителей"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список усыновителей"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<Adopter>> getAllUsers() {
        return ResponseEntity.ok(adopterService.getAllAdopters());
    }

    @PostMapping
    @Operation(
            summary = "Регистрация усыновителя",
            description = "Нужно написать данные усыновителя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось добавить усыновителя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public Adopter createAdopter(@RequestBody Adopter adopter) {
        return adopterService.createAdopter(adopter);
    }

    @PutMapping
    @Operation(
            summary = "Изменения данных усыновителя",
            description = "Нужно написать новые данные усыновителя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось изменить данне усыновителя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Adopter> changeAdopter(@RequestBody Adopter adopter) {
        Adopter foundAdopter = adopterService.changeAdopter(adopter);
        if (foundAdopter == null) {
            throw new NotFoundAdopterException("Усыновитель не найден в базе данных!");
        }
        return ResponseEntity.ok(foundAdopter);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление усыновителя по идентификатору",
            description = "Нужно написать id усыновителя, которого нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить усыновителя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Adopter> deleteAdopter(@PathVariable Long id) {
        adopterService.deleteAdopter(id);
        return ResponseEntity.ok().build();
    }
}