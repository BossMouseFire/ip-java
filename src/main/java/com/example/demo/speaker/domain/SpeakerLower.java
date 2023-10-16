package com.example.demo.speaker.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpeakerLower implements  Speaker {
    private final Logger log = LoggerFactory.getLogger(SpeakerLower.class);
    @Override
    public String say(String name) {
        return "привет " + name.toLowerCase();
    }

    public void init() {
        log.info("SpeakerLower.init()");
    }

    public void destroy() {
        log.info("SpeakerLower.destroy()");
    }
}
