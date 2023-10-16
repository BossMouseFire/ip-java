package com.example.demo.speaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.speaker.service.SpeakerService;

import java.util.List;

@Controller
@RequestMapping("/speaker")
public class SpeakerMvcController {
    private final SpeakerService speakerService;
    private final List<String> sizes;

    public SpeakerMvcController(SpeakerService speakerService) {
        this.speakerService = speakerService;
        this.sizes = List.of("low", "up");
    }

    @GetMapping
    public String hello(@RequestParam(value = "name", defaultValue = "Мир") String name,
                        @RequestParam(value = "size", defaultValue = "low") String size,
                        Model model) {
        model.addAttribute("sizes", sizes);
        model.addAttribute("name", name);
        model.addAttribute("size", size);
        model.addAttribute("say", speakerService.say(name, size));
        return "speaker";
    }
}
