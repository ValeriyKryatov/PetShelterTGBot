package PetShelterTGBot.controller;

import PetShelterTGBot.exception.NotFoundUserException;
import PetShelterTGBot.model.User;
import PetShelterTGBot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер пользователей.
 */
@RestController
@RequestMapping("/pet_shelter/user")
@Tag(name = "API по работе с пользователями",
        description = "CRUD-операции для работы с пользователями")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск пользователя по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить пользователя по id"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<User> getUserInfo(@PathVariable Long id) {
        User user = userService.readUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping
    @Operation(
            summary = "Список всех пользователей"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список пользователей"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @Operation(
            summary = "Регистрация пользователя",
            description = "Нужно написать данные пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось добавить пользователя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping
    @Operation(
            summary = "Изменения данных пользователя",
            description = "Нужно написать новые данные пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось изменить данне пользователя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        User foundUser = userService.changeUser(user);
        if (foundUser == null) {
            throw new NotFoundUserException("Пользователь не найден в базе данных!");
        }
        return ResponseEntity.ok(foundUser);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление пользователя по идентификатору",
            description = "Нужно написать id пользователя, которого нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить пользователя"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}