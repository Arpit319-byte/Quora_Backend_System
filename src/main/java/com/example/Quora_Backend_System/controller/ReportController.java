package com.example.Quora_Backend_System.controller;

import com.example.Quora_Backend_System.model.Report;
import com.example.Quora_Backend_System.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@Tag(name = "Reports", description = "Operations related to report")
public class ReportController {

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getAllReports() {
        logger.info("Getting all reports");
        return ResponseEntity.ok(reportService.getAllReports());
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<Report> getReportById(@PathVariable Long reportId) {
        logger.info("Getting report by id - " + reportId);
        Report report = reportService.getReportById(reportId);
        if (report == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @PostMapping
    public ResponseEntity<Report> createReport(@Valid @RequestBody Report report) {
        logger.info("Creating report");
        return ResponseEntity.ok(reportService.createReport(report));
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<Report> updateReport(@PathVariable Long reportId, @Valid @RequestBody Report report) {
        logger.info("Updating report by id - " + reportId);
        Report updatedReport = reportService.updateReport(reportId, report);
        if (updatedReport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long reportId) {
        logger.info("Deleting report by id - " + reportId);
        reportService.deleteReport(reportId);
        return ResponseEntity.noContent().build();
    }
}
