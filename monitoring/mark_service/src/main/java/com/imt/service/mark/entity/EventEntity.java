package com.imt.service.mark.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "eventtable")
public class EventEntity {
    @Id
    private UUID id;
    @Column
    private String title;
    @Column
    private UUID organizer;
    @Column
    private LocalDateTime creationDatetime;
    @OneToMany(mappedBy="event")
    private Set<MarkEntity> marks;

    public EventEntity() {
        super();
        this.marks = new HashSet<>();
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

    public UUID getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UUID organizer) {
        this.organizer = organizer;
    }

    public LocalDateTime getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(LocalDateTime creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public Set<MarkEntity> getMarks() {
        return marks;
    }

    public void setMarks(Set<MarkEntity> marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "EventEntity [id=" + id + ", title=" + title + ", organizer=" + organizer + ", creationDatetime="
                + creationDatetime + "]";
    }
}
