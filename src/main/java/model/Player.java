package model;


import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class Player {
    private int id;
    private int teamId;
    private String name;
    private String position;
    private Timestamp createdAt;

    public Player(int id, int teamId, String name, String position, Timestamp createdAt) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}