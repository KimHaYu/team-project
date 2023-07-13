package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Stadium;
import model.Team;

import java.time.LocalDateTime;


//전체 팀 목록
@Getter
@AllArgsConstructor
public class TeamRespDTO {

    private Integer teamId;
    private String teamName;

    private StadiumRespDTO stadium;

    private LocalDateTime teamCreatedAt;

    public TeamRespDTO(Team team, Stadium stadium) {
        this.teamId = team.getTeamId();
        this.teamName = team.getTeamName();
        this.stadium = new StadiumRespDTO(stadium);
        this.teamCreatedAt = team.getTeamCreatedAt();
    }

    @Override
    public String toString() {
        return "TeamRespDTO{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", stadium=" + stadium +
                ", teamCreatedAt=" + teamCreatedAt +
                '}';
    }

    @Getter
    @AllArgsConstructor
    public static class StadiumRespDTO {
        private Integer stadiumId;
        private String stadiumName;
        private LocalDateTime stadiumCreatedAt;

        public StadiumRespDTO(Stadium stadium) {
            this.stadiumId = stadium.getStadiumId();
            this.stadiumName = stadium.getStadiumName();
            this.stadiumCreatedAt = stadium.getStadiumCreated_at();
        }

        @Override
        public String toString() {
            return "StadiumRespDTO{" +
                    "stadiumId=" + stadiumId +
                    ", stadiumName='" + stadiumName + '\'' +
                    ", stadiumCreatedAt=" + stadiumCreatedAt +
                    '}';
        }
    }

}
