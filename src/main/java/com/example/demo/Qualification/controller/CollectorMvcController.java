package com.example.demo.Qualification.controller;

import com.example.demo.Qualification.model.Collector;
import com.example.demo.Qualification.model.Qualification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.Qualification.service.QualificationService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/collector")
public class CollectorMvcController {
    private final QualificationService qualificationService;

    public CollectorMvcController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }
    @GetMapping
    public String getCollector(Model model) {
        model.addAttribute("collectors",
                qualificationService.findAllCollectors());
        return "collector";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editCollector(@PathVariable(required = false) Long id,
                                    Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("collector", new Collector());
        } else {
            model.addAttribute("collectorId", id);
            model.addAttribute("collector",qualificationService.findCollector(id));
        }
        return "collector-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveCollector(@PathVariable(required = false) Long id,
                                    @ModelAttribute @Valid Collector collector,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "collector-edit";
        }
        if (id == null || id <= 0) {
            qualificationService.addCollector(collector.getExperience(), collector.getName(), collector.getQualification().getId());
        } else {
            qualificationService.updateCollector(id, collector.getExperience(), collector.getName(), collector.getQualification().getId());
        }
        return "redirect:/collector";
    }
    @PostMapping("/delete/{id}")
    public String deleteCollector(@PathVariable Long id) {
        qualificationService.deleteCollector(id);
        return "redirect:/collector";
    }
}
