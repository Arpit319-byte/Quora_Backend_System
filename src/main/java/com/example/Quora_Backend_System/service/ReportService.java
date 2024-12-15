package com.example.Quora_Backend_System.service;

import com.example.Quora_Backend_System.model.Report;
import com.example.Quora_Backend_System.repository.ReportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final ReportRepository reportRepository;

    @Autowired
    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<Report> getAllReports() {
        logger.info("Getting all reports");
        return reportRepository.findAll();
    }

    public Report getReportById(Long reportId) {
        logger.info("Getting report by id - " + reportId);
        return reportRepository.findById(reportId).orElse(null);
    }

    public Report createReport(Report report) {
        logger.info("Creating report");
        return reportRepository.save(report);
    }

    public Report updateReport(Long reportId, Report report) {
        logger.info("Updating report by id - " + reportId);
        Report updatedReport = reportRepository.findById(reportId).orElse(null);
        if (updatedReport == null) {
            logger.warn("Report not found");
            return null;
        }
        return reportRepository.save(updatedReport);
    }

    public void deleteReport(Long reportId) {
        logger.info("Deleting report by id - " + reportId);
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            logger.warn("Report not found");
            return;
        }
        reportRepository.delete(report);
    }
}
