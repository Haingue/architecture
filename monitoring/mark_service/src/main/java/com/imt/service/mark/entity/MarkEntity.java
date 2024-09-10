package com.imt.service.mark.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "marktable")
public class MarkEntity {

    @Id
    private UUID id;
    @Column
    private UUID participant;
    @ManyToOne(cascade = CascadeType.ALL)
    private EventEntity event;
    @Column
    private int markValue;
    @Column
    private LocalDateTime creationDatetime;

    public MarkEntity() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getParticipant() {
        return participant;
    }

    public void setParticipant(UUID participant) {
        this.participant = participant;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int value) {
        this.markValue = value;
    }

    public LocalDateTime getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(LocalDateTime creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    @Override
    public String toString() {
        return "MarkEntity [id=" + id + ", participant=" + participant + ", event=" + event + ", value=" + markValue
                + ", creationDatetime=" + creationDatetime + "]";
    }
}
