package com.azure.bau.aiproject.controller;
import com.azure.bau.aiproject.model.Analysis;
import com.azure.bau.aiproject.service.AnalysisService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @PostMapping("/analyze")
    public @ResponseBody List<String> analyze(@RequestBody String text) {
        return analysisService.extractKeyPhrases(text);
    }

    @GetMapping("/history")
    public String history(Model model,
                          @RequestParam(name = "page", defaultValue = "0") int page,
                          @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Analysis> analysisPage = analysisService.getHistory(pageable);
            model.addAttribute("history", analysisPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", analysisPage.getTotalPages());
            model.addAttribute("pageNumbers", IntStream.rangeClosed(0, analysisPage.getTotalPages() - 1).toArray());
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "history";
    }

}