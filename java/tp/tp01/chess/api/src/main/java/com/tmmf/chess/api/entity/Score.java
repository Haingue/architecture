package com.tmmf.chess.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Score {

    @Id
    private String uuid;
    private int value;
    private LocalDateTime createdAt;

    public Score() {
    }

    public Score(String uuid, int value, LocalDateTime createdAt) {
        this.uuid = uuid;
        this.value = value;
        this.createdAt = createdAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Score score))
            return false;
        return Objects.equals(uuid, score.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "Score{" +
                "uuid='" + uuid + '\'' +
                ", value=" + value +
                ", createdAt=" + createdAt +
                '}';
    }
}
