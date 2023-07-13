package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Stadium {
    private Integer stadiumId;
    private String stadiumName;
    private LocalDateTime stadiumCreated_at;

    @Override
    public String toString() {
        return "Stadium{" +
                "stadiumId=" + stadiumId +
                ", stadiumName='" + stadiumName + '\'' +
                ", stadiumCreated_at=" + stadiumCreated_at +
                '}';
    }
}
