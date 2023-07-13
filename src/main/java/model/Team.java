package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Team {
    private Integer teamId;
    private Integer stadiumId;
    private String teamName;
    private LocalDateTime teamCreatedAt;
}
