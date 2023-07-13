package service;

import dao.PlayerDAO;

//선수 비즈니스 로직
public class PlayerService {

    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void createPlayer(int teamId, String playerName, String position) {
        playerDAO.insert(teamId, playerName, position);
    }
}
