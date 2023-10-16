package com.example.demo;


import com.example.demo.Qualification.model.Collector;
import com.example.demo.Qualification.model.Qualification;
import com.example.demo.Qualification.service.CollectorNotFoundException;
import com.example.demo.Qualification.service.QualificationNotFoundException;
import com.example.demo.Qualification.service.QualificationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@SpringBootTest
public class JpaQualificationCollectorTests {
    private static final Logger log = LoggerFactory.getLogger(JpaQualificationCollectorTests.class);

    @Autowired
    private QualificationService qualificationService;

    @Test
    void testQualificationCreate() {
        qualificationService.deleteAllQualifications();
        final Qualification qualification = qualificationService.addQualification(1, "прораб");
        log.info(qualification.toString());
        Assertions.assertNotNull(qualification.getId());
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testQualificationRead() {
        qualificationService.deleteAllQualifications();
        final Qualification qualification = qualificationService.addQualification(1, "прораб");
        log.info(qualification.toString());
        final Qualification findQualification = qualificationService.findQualification(qualification.getId());
        log.info(findQualification.toString());
        Assertions.assertEquals(qualification, findQualification);
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testQualificationReadNotFound() {
        qualificationService.deleteAllQualifications();
        Assertions.assertThrows(QualificationNotFoundException.class, () -> qualificationService.findQualification(-1L));
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testQualificationReadAll() {
        qualificationService.deleteAllQualifications();
        qualificationService.addQualification(1, "прораб");
        qualificationService.addQualification(2, "помошник");
        final List<Qualification> qualifications = qualificationService.findAllQualifications();
        log.info(qualifications.toString());
        Assertions.assertEquals(qualifications.size(), 2);
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testQualificationReadAllEmpty() {
        qualificationService.deleteAllQualifications();
        final List<Qualification> qualifications = qualificationService.findAllQualifications();
        log.info(qualifications.toString());
        Assertions.assertEquals(qualifications.size(), 0);
        qualificationService.deleteAllQualifications();
    }
    @Test
    void testCollectorCreate() {
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
        long qualification = qualificationService.addQualification(1, "test").getId();
        final Collector collector = qualificationService.addCollector(1, "vova", qualification);
        log.info(collector.toString());
        Assertions.assertNotNull(collector.getId());
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
    }
    @Test
    void testCollectorRead() {
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
        Qualification qualification = qualificationService.addQualification(1, "test");
        final Collector collector = qualificationService.addCollector(1, "vova", qualification.getId());
        log.info(collector.toString());
        final Collector findCollector = qualificationService.findCollector(collector.getId());
        log.info(findCollector.toString());
        Assertions.assertEquals(collector, findCollector);
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testCollectorReadNotFound() {
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
        Assertions.assertThrows(CollectorNotFoundException.class, () -> qualificationService.findCollector(-1L));
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testCollectorReadAll() {
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
        Qualification qualification = qualificationService.addQualification(1, "test");
        qualificationService.addCollector(1, "vova", qualification.getId());
        qualificationService.addCollector(2, "vovan", qualification.getId());
        final List<Collector> collectors = qualificationService.findAllCollectors();
        log.info(collectors.toString());
        Assertions.assertEquals(collectors.size(), 2);
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
    }

    @Test
    void testCollectorReadAllEmpty() {
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
        final List<Collector> collectors = qualificationService.findAllCollectors();
        log.info(collectors.toString());
        Assertions.assertEquals(collectors.size(), 0);
        qualificationService.deleteAllCollectors();
        qualificationService.deleteAllQualifications();
    }
}
