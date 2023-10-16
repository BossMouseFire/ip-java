package com.example.demo.Qualification.service;

public class CollectorNotFoundException extends RuntimeException {
    public CollectorNotFoundException(Long id) {
        super(String.format("Collector with id [%s] is not found", id));
    }
}
