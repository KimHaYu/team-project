package service;

import dao.OutPlayerDAO;
import dao.PlayerDAO;
import dto.OutPlayerRespDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//퇴출선수 비즈니스 로직
public class OutPlayerService {

    private final Connection con;
    private final OutPlayerDAO outPlayerDAO;
    private final PlayerDAO playerDAO;

    public OutPlayerService(Connection con, OutPlayerDAO outPlayerDAO, PlayerDAO playerDAO) {
        this.con = con;
        this.outPlayerDAO = outPlayerDAO;
        this.playerDAO = playerDAO;
    }


    public void createOutPlayer(int playerId, String reason) {
        try {
            con.setAutoCommit(false);
            outPlayerDAO.insert(playerId, reason);
            playerDAO.playerOut(playerId);
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OutPlayerRespDTO> getAllPlayersWithReason() {
        return outPlayerDAO.selectAllPlayersWithOutReason();
    }
}
