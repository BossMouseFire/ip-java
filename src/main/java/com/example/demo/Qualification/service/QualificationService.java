package com.example.demo.Qualification.service;

import com.example.demo.Qualification.model.Collector;
import com.example.demo.Qualification.repository.CollectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Qualification.model.Qualification;
import com.example.demo.Qualification.repository.QualificationRepository;
import com.example.demo.util.validation.ValidatorUtil;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class QualificationService {
    private final QualificationRepository qualificationRepository;
    private final CollectorRepository collectorRepository;
    private final ValidatorUtil validatorUtil;

    public QualificationService(QualificationRepository qualificationRepository,
                                ValidatorUtil validatorUtil,
                                CollectorRepository collectorRepository) {
        this.qualificationRepository = qualificationRepository;
        this.validatorUtil = validatorUtil;
        this.collectorRepository = collectorRepository;
    }

    @Transactional
    public Qualification addQualification(int category, String name) {
        final Qualification qualification = new Qualification(category, name);
        validatorUtil.validate(qualification);
        return qualificationRepository.save(qualification);
    }

    @Transactional(readOnly = true)
    public Qualification findQualification(Long id) {
        final Optional<Qualification> qualification = qualificationRepository.findById(id);
        return qualification.orElseThrow(() -> new QualificationNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Qualification> findAllQualifications() {
        return qualificationRepository.findAll();
    }

    @Transactional
    public Qualification updateQualification(Long id, int category, String name) {
        final Qualification currentQualification = findQualification(id);
        currentQualification.setCategory(category);
        currentQualification.setName(name);
        validatorUtil.validate(currentQualification);
        return qualificationRepository.save(currentQualification);
    }

    @Transactional
    public Qualification deleteQualification(Long id) {
        final Qualification currentQualification = findQualification(id);
        qualificationRepository.delete(currentQualification);
        return currentQualification;
    }

    @Transactional
    public void deleteAllQualifications() {
        qualificationRepository.deleteAll();
    }

    @Transactional
    public Collector addCollector(int experience, String name, long qualificationId) {
        Qualification qualification = findQualification(qualificationId);
        final Collector collector = new Collector(experience, name, qualification);
        validatorUtil.validate(collector);
        return collectorRepository.save(collector);
    }

    @Transactional(readOnly = true)
    public Collector findCollector(Long id) {
        final Optional<Collector> collector = collectorRepository.findById(id);
        return collector.orElseThrow(() -> new CollectorNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Collector> findAllCollectors() {
        return collectorRepository.findAll();
    }

    @Transactional
    public Collector updateCollector(Long id, int experience, String name, long qualificationId) {
        final Collector currentCollector = findCollector(id);
        currentCollector.setExperience(experience);
        currentCollector.setName(name);
        currentCollector.setQualification(findQualification(qualificationId));
        validatorUtil.validate(currentCollector);
        return collectorRepository.save(currentCollector);
    }

    @Transactional
    public Collector deleteCollector(Long id) {
        final Collector currentCollector = findCollector(id);
        collectorRepository.delete(currentCollector);
        return currentCollector;
    }

    @Transactional
    public void deleteAllCollectors() {
        collectorRepository.deleteAll();
    }
}
