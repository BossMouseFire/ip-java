package com.example.demo.speaker.service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.example.demo.speaker.domain.Speaker;

@Service
public class SpeakerService {
    private final ApplicationContext applicationContext;

    public SpeakerService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String say(String name, String size) {
        final Speaker speaker = (Speaker) applicationContext.getBean(size);
        return String.format("%s!", speaker.say(name));
    }
}
