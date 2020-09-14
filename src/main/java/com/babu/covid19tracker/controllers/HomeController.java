package com.babu.covid19tracker.controllers;

import com.babu.covid19tracker.models.LocationStats;
import com.babu.covid19tracker.services.Covid19DataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    final Covid19DataService covid19DataService;

    public HomeController(Covid19DataService covid19DataService) {
        this.covid19DataService = covid19DataService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = covid19DataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStats::getDiffFromPrevDay).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
