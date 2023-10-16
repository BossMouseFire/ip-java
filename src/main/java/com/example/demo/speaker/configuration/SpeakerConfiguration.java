package com.example.demo.speaker.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.speaker.domain.SpeakerLower;
import com.example.demo.speaker.domain.SpeakerUpper;

@Configuration
public class SpeakerConfiguration {
    private final Logger log = LoggerFactory.getLogger(SpeakerConfiguration.class);

    @Bean(value = "low", initMethod = "init", destroyMethod = "destroy")
    public SpeakerLower createLowerSpeaker() {
        log.info("Call createLowerSpeaker()");
        return new SpeakerLower();
    }
    @Bean(value = "up", initMethod = "init", destroyMethod = "destroy")
    public SpeakerUpper createUpperSpeaker() {
        log.info("Call createUpperSpeaker()");
        return new SpeakerUpper();
    }
}
