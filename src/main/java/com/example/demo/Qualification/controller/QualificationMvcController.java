package com.example.demo.Qualification.controller;

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
@RequestMapping("/qualification")
public class QualificationMvcController {
    private final QualificationService qualificationService;

    public QualificationMvcController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping
    public String getQualifications(Model model) {
        model.addAttribute("qualifications",
                qualificationService.findAllQualifications());
        return "qualification";
    }
    @GetMapping(value = {"/edit", "/edit/{id}"})
    public String editQualification(@PathVariable(required = false) Long id,
                              Model model) {
        if (id == null || id <= 0) {
            model.addAttribute("qualification", new Qualification());
        } else {
            model.addAttribute("qualificationId", id);
            model.addAttribute("qualification",qualificationService.findQualification(id));
        }
        return "qualification-edit";
    }
    @PostMapping(value = {"", "/{id}"})
    public String saveQualification(@PathVariable(required = false) Long id,
                              @ModelAttribute @Valid Qualification qualification,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "qualification-edit";
        }
        if (id == null || id <= 0) {
            qualificationService.addQualification(qualification.getCategory(), qualification.getName());
        } else {
            qualificationService.updateQualification(id, qualification.getCategory(), qualification.getName());
        }
        return "redirect:/qualification";
    }
    @PostMapping("/delete/{id}")
    public String deleteQualification(@PathVariable Long id) {
        qualificationService.deleteQualification(id);
        return "redirect:/qualification";
    }
}
