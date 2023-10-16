package com.example.demo.Qualification.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Collector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Category can't be null")
    private int experience;
    @NotBlank(message = "name can't be null or empty")
    private String name;

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Qualification qualification;

    public Collector() {
    }

    public Collector(int experience, String name, Qualification qualification) {
        this.experience = experience;
        this.name = name;
        this.qualification = qualification;
    }
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collector collector = (Collector) o;
        return Objects.equals(id, collector.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Collector{" +
                "id=" + id +
                ", Experience='" + experience + '\'' +
                ", Name='" + name + '\'' +
                ", Qualification='" + qualification + '\'' +
                '}';
    }
}
