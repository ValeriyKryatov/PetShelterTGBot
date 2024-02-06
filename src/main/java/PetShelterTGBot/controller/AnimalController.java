package PetShelterTGBot.controller;

import PetShelterTGBot.exception.NotFoundAnimalException;
import PetShelterTGBot.model.Animal;
import PetShelterTGBot.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер животных.
 */
@RestController
@RequestMapping("/pet_shelter/animal")
@Tag(name = "API по работе с животными",
        description = "CRUD-операции для работы с животными")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск животного по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить животное по id"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Animal> getAnimalInfo(@PathVariable Long id) {
        Animal animal = animalService.readAnimal(id);
        if (animal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(animal);
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
    public ResponseEntity<Collection<Animal>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @PostMapping
    @Operation(
            summary = "Регистрация животного",
            description = "Нужно написать данные животного"
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
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalService.createAnimal(animal);
    }

    @PutMapping
    @Operation(
            summary = "Изменения данных животного",
            description = "Нужно написать новые данные животного"
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
    public ResponseEntity<Animal> changeAnimal(@RequestBody Animal animal) {
        Animal foundAnimal = animalService.changeAnimal(animal);
        if (foundAnimal == null) {
            throw new NotFoundAnimalException("Животное не найдено в базе данных!");
        }
        return ResponseEntity.ok(foundAnimal);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление животного по идентификатору",
            description = "Нужно написать id животного, которое нужно удалить"
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
    public ResponseEntity<Animal> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }
}