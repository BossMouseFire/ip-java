package com.example.demo;

import com.example.demo.speaker.service.SpeakerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	SpeakerService speakerService;

	@Test
	void testSpeakerLow() {
		final String res = speakerService.say("Мир", "low");
		Assertions.assertEquals("привет мир!", res);
	}
	@Test
	void testSpeakerUp() {
		final String res = speakerService.say("Мир", "up");
		Assertions.assertEquals("ПРИВЕТ МИР!", res);
	}
	@Test
	void testSpeakerErrorWired() {
		//Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> speakerService.say("Мир", "up"));
	}

}
