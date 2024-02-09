package PetShelterTGBot.controller;

import PetShelterTGBot.model.Report;
import PetShelterTGBot.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Контроллер отчетов.
 */
@RestController
@RequestMapping("/pet_shelter/report")
@Tag(name = "API по работе с отчетами",
        description = "CRUD-операции для работы с отчетами")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    @Operation(
            summary = "Список всех отчетов"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось получить список отчетов"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Collection<Report>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление отчета по идентификатору",
            description = "Нужно написать id отчета, который нужно удалить"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить отчет"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Параметры запроса отсутствуют или имеют некорректный формат"
    )
    @ApiResponse(
            responseCode = "500",
            description = "Произошла ошибка, не зависящая от вызывающей стороны"
    )
    public ResponseEntity<Report> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok().build();
    }
}