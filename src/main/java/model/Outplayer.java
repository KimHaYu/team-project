package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
@AllArgsConstructor
public class Outplayer {
    private Integer outPlayerId;
    private Integer playerId;
    private String reason;
    private Timestamp outPlayerCreatedAt;
}
