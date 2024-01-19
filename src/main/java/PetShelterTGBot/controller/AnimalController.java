package PetShelterTGBot.controller;

import PetShelterTGBot.constants.Color;
import PetShelterTGBot.constants.PetType;
import PetShelterTGBot.constants.Gender;
import PetShelterTGBot.model.Animal;
import PetShelterTGBot.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(value = "/pet-shelter/animal")
@Tag(name = "API по работе с животными",
        description = "CRUD-операции для работы с животными")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    @Operation(
            summary = "Регистрация животного",
            description = "Нужно написать данные животного " +
                    "(кличка, тип животного, цвет и пол)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось добавить животное"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Animal> saveAnimal(@RequestParam(required = false) String nickName,
                                             @RequestParam(required = false) PetType petType,
                                             @RequestParam(required = false) Color color,
                                             @RequestParam(required = false) Gender gender) {
        try {
            return ResponseEntity.ok(animalService.saveAnimal(nickName, petType, color, gender));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменения данных животного по идентификатору",
            description = "Нужно написать новые данные животного " +
                    "(кличка, тип животного, цвет и пол)"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось изменить данне животного"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Void> updateAdopter(@PathVariable Long id,
                                              @RequestParam(required = false) String nickName,
                                              @RequestParam(required = false) PetType petType,
                                              @RequestParam(required = false) Color color,
                                              @RequestParam(required = false) Gender gender) {

        try {
            animalService.updateAnimalById(id, nickName, petType, color, gender);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление животного по идентификатору",
            description = "Нужно написать id животного, которого нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить животное"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Void> deleteAdopterById(@PathVariable Long id) {

        try {
            animalService.deleteAnimalById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(
            summary = "Список всех животных"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список животных"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<Animal>> getAllAnimal() {

        try {
            return ResponseEntity.ok(animalService.getAllAnimal());
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}