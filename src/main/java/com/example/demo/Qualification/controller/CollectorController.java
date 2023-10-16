package com.example.demo.Qualification.controller;

import com.example.demo.Qualification.model.Collector;
import com.example.demo.WebConfiguration;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Qualification.service.QualificationService;

import java.util.List;


@RestController
@RequestMapping(WebConfiguration.REST_API + "/collector")
public class CollectorController {
    private final QualificationService qualificationService;

    public CollectorController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @GetMapping("/{id}")
    public Collector getCollector(@PathVariable Long id) {
        return qualificationService.findCollector(id);
    }

    @GetMapping
    public List<Collector> getCollectors() {
        return qualificationService.findAllCollectors();
    }
    @PostMapping
    public Collector createCollector(@RequestParam("experience") int experience,
                                     @RequestParam("name") String name,
                                     @RequestParam("qualificationId") long qualificationId) {
        return qualificationService.addCollector(experience, name, qualificationId);
    }
    @PatchMapping("/{id}")
    public Collector updateCollector(@PathVariable Long id,
                                     @RequestParam("experience") int experience,
                                     @RequestParam("name") String name,
                                     @RequestParam("qualificationId") long qualificationId) {
        return qualificationService.updateCollector(id, experience, name, qualificationId);
    }
    @DeleteMapping("/{id}")
    public Collector deleteCollector(@PathVariable Long id) {
        return qualificationService.deleteCollector(id);
    }
}
