package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//선수 퇴출 목록
@Getter
@AllArgsConstructor
public class OutPlayerRespDTO {

    private Integer playerId;
    private String playerName;
    private String playerPosition;
    private String reason;
    private LocalDateTime day;

    @Override
    public String toString() {
        return "OutPlayerRespDTO{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", playerPosition='" + playerPosition + '\'' +
                ", reason='" + reason + '\'' +
                ", day=" + (day == null ? null : day.format(DateTimeFormatter.ISO_DATE)) +
                '}';
    }
}
