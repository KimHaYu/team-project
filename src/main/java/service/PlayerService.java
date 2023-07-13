package service;

import dao.PlayerDAO;

//선수 비즈니스 로직
public class PlayerService {

    private final PlayerDAO playerDAO;

    public PlayerService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void createPlayer(int teamId, String name, String position) {
        playerDAO.insert(teamId, name, position);
    }
}