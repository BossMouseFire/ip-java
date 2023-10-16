package com.example.demo.speaker.controller;

import com.example.demo.WebConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.speaker.service.SpeakerService;

@RestController
@RequestMapping(WebConfiguration.REST_API + "/speaker")
public class SpeakerController {
    private final SpeakerService speakerService;
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "Мир") String name,
                        @RequestParam(value = "size", defaultValue = "up") String size) {
        return speakerService.say(name, size);
    }
}
