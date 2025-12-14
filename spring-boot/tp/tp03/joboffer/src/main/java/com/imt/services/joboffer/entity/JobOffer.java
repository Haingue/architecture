package com.imt.services.joboffer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class JobOffer {

    @Id
    private UUID id;
    private String title;
    private String description;
    private UUID studentId;
    @ManyToOne
    private Company owner;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startDayTime;
    private LocalTime endDayTime;
    private int expirationDays;
    private Instant creationTimestamp;

    public JobOffer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartDayTime() {
        return startDayTime;
    }

    public void setStartDayTime(LocalTime startDayTime) {
        this.startDayTime = startDayTime;
    }

    public LocalTime getEndDayTime() {
        return endDayTime;
    }

    public void setEndDayTime(LocalTime endDayTime) {
        this.endDayTime = endDayTime;
    }

    public int getExpirationDays() {
        return expirationDays;
    }

    public void setExpirationDays(int expirationDays) {
        this.expirationDays = expirationDays;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company company) {
        this.owner = company;
    }

    public boolean isAssigned () {
        return studentId != null;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof JobOffer jobOffer)) return false;
        return expirationDays == jobOffer.expirationDays && Objects.equals(id, jobOffer.id) && Objects.equals(title, jobOffer.title) && Objects.equals(description, jobOffer.description) && Objects.equals(startDate, jobOffer.startDate) && Objects.equals(endDate, jobOffer.endDate) && Objects.equals(startDayTime, jobOffer.startDayTime) && Objects.equals(endDayTime, jobOffer.endDayTime);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
