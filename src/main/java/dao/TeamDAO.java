package dao;

import dto.TeamRespDTO;
import model.Stadium;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//팀 테이블
public class TeamDAO {
    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    // 팀 삽입
    public int insert(int stadiumId, String teamName) {
        // sql문 작성
        String sql = "insert into team(stadium_id, name, created_at) values(?, ?, now())";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, stadiumId);
            ps.setString(2, teamName);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // 팀 수정
    public void update() {
        //      sql문 작성
        String sql = "";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 팀 삭제
    public void delete() {
        //      sql문 작성
        String sql = "";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 팀 하나 찾기
    public void selectOne() {
        String sql = " ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<TeamRespDTO> selectAllWithStadium() {
        List<TeamRespDTO> teamDtos = new ArrayList<>();

        String sql = "select t.id as team_id,\n" +
                "       t.name as team_name,\n" +
                "       t.created_at as team_created_at,\n" +
                "       t.stadium_id as team_stadium_id,\n" +
                "       s.id as stadium_id,\n" +
                "       s.name as stadium_name,\n" +
                "       s.created_at as stadium_created_at\n" +
                "from team t left join stadium s on t.stadium_id = s.id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int teamId = rs.getInt("team_id");
                String teamName = rs.getString("team_name");
                LocalDateTime teamCreatedAt = rs.getTimestamp("team_created_at").toLocalDateTime();

                int stadiumId = rs.getInt("stadium_id");
                String stadiumName = rs.getString("stadium_name");
                LocalDateTime stadiumCreatedAt = rs.getTimestamp("stadium_created_at").toLocalDateTime();

                Team team = new Team(teamId, stadiumId, teamName, teamCreatedAt);
                Stadium stadium = new Stadium(stadiumId, stadiumName, stadiumCreatedAt);

                teamDtos.add(new TeamRespDTO(team, stadium));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teamDtos;
    }

    // 팀 전체 찾기
    // (안에 안 적어놔서 오류납니다, 안에 작성할 때 주석 푸시면 됩니다)
//    public List<OutPlayer> findAll() {
//        list<OutPlayer> OutplayerDaoAll = new ArrayList<>();
//
//        String sql = "";
//
//        try {
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()){
//
//                );
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return OutplayerDaoAll;
//    }


}
