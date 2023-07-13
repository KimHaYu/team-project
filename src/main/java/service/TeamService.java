package service;

import dao.TeamDAO;
import dto.TeamRespDTO;

import java.util.List;

//팀 비즈니스 로직
public class TeamService {

    private final TeamDAO teamDAO;

    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public int createTeam(int stadiumId, String teamName) {
        return teamDAO.insert(stadiumId, teamName);
    }

    public List<TeamRespDTO> getAllTeam() {
        return teamDAO.selectAllWithStadium();
    }
}
