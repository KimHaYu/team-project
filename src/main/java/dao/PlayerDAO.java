
package dao;

import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//선수 테이블
public class PlayerDAO {
    private Connection connection;
    public PlayerDAO(Connection connection){
        this.connection = connection;
    }

    public int insertPlayer(int teamId, String name, String position) throws SQLException {
        int result = 0;
        String query = "INSERT INTO player_tb (team_id, name, position, created_at) VALUES (?, ?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            result = statement.executeUpdate();
        }
        return result;
    }


    public List<Player> getPlayerList() {
        List<Player> playerList = new ArrayList<>();

        // SQL 문을 실행하여 ResultSet에서 데이터를 가져옴
        String sql = "SELECT * FROM player";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int playerId = rs.getInt("id");
                int teamId = rs.getInt("team_id");
                String playerName = rs.getString("name");
                String position = rs.getString("position");
                Timestamp playerCreatedAt = rs.getTimestamp("created_at");

                // Player 객체 생성 및 리스트에 추가
                Player player = new Player(playerId, teamId, playerName, position, playerCreatedAt);
                playerList.add(player);
            }

            // ResultSet, PreparedStatement, Connection 등의 자원을 해제
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerList;
    }

    public List<Player> getPlayerListByTeam(int teamId) {
        List<Player> playerList = new ArrayList<>();

        // SQL 문을 실행하여 ResultSet에서 데이터를 가져옴
        String sql = "SELECT * FROM player WHERE team_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, teamId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int playerId = rs.getInt("id");
                String playerName = rs.getString("name");
                String position = rs.getString("position");
                Timestamp playerCreatedAt = rs.getTimestamp("created_at");

                // Player 객체 생성 및 리스트에 추가
                Player player = new Player(playerId, teamId, playerName, position, playerCreatedAt);
                playerList.add(player);
            }

            // ResultSet, PreparedStatement, Connection 등의 자원을 해제
            rs.close();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerList;
    }

    public void playerOut(int playerId) {
    }

    public void insert(int teamId, String name, String position) {
    }
}




