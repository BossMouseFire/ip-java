package com.example.demo.Qualification.controller;

import com.example.demo.Qualification.model.Collector;
import com.example.demo.Qualification.model.Qualification;
import com.example.demo.WebConfiguration;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Qualification.service.QualificationService;

import java.util.List;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/qualification")
public class QualificationController {
    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("/{id}")
    public Qualification getQualification(@PathVariable Long id) {
        return qualificationService.findQualification(id);
    }

    @GetMapping
    public List<Qualification> getQualifications() {
        return qualificationService.findAllQualifications();
    }

    @PostMapping
    public Qualification createQualification(@RequestParam("category") int category,
                                          @RequestParam("name") String name) {
        return qualificationService.addQualification(category, name);
    }

    @PutMapping("/{id}")
    public Qualification updatQualification(@PathVariable Long id,
                                          @RequestParam("category") int category,
                                          @RequestParam("name") String name) {
        return qualificationService.updateQualification(id, category, name);
    }

    @DeleteMapping("/{id}")
    public Qualification deleteQualification(@PathVariable Long id) {
        return qualificationService.deleteQualification(id);
    }
}