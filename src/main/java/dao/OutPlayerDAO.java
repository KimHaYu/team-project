package dao;

import dto.OutPlayerRespDTO;
import dto.TeamRespDTO;
import model.Stadium;
import model.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//퇴출 선수 테이블
public class OutPlayerDAO {
    private Connection connection;

    public OutPlayerDAO(Connection connection) {
        this.connection = connection;
    }

    // 퇴출 선수 삽입
    public int insert(int playerId, String reason) {
        // sql문 작성
        String sql = "insert into out_player(player_id, reason, created_at) values(?, ?, now())";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, playerId);
            ps.setString(2, reason);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    // 퇴출 선수 수정
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

    // 퇴출 선수 삭제
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

    // 퇴출 선수 하나 찾기
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

    public List<OutPlayerRespDTO> selectAllPlayersWithOutReason() {
        List<OutPlayerRespDTO> outPlayerRespDTOList = new ArrayList<>();

        String sql = "select p.id as player_id,\n" +
                "       p.name as player_name,\n" +
                "       p.position as player_position,\n" +
                "       op.reason as out_reason,\n" +
                "       op.created_at as out_day\n" +
                "from player p left outer join out_player op on p.id = op.player_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int playerId = rs.getInt("player_id");
                String playerName = rs.getString("player_name");
                String playerPosition = rs.getString("player_position");
                String outReason = rs.getString("out_reason");
                Timestamp outDayTimeStamp = rs.getTimestamp("out_day");
                LocalDateTime outDay = null;
                if (outDayTimeStamp != null) outDay = outDayTimeStamp.toLocalDateTime();

                outPlayerRespDTOList.add(new OutPlayerRespDTO(playerId, playerName, playerPosition, outReason, outDay));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outPlayerRespDTOList;
    }

    // 퇴출 선수 전체 찾기
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
