package dao;

import java.sql.*;

//선수 테이블
public class PlayerDAO {
    private Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    // 선수 삽입
    public int insert(int teamId, String playerName, String position) {
        // sql문 작성
        String sql = "insert into player(team_id, name, position, created_at) values(?, ?, ?, now())";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, teamId);
            ps.setString(2, playerName);
            ps.setString(3, position);
            return ps.executeUpdate();// <- DB에 쿼리 전송!!
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("중복입니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // 오류 뜨면 -1, 성공했으면 1 반환
    }

    // 선수 수정
    public void playerOut(int playerId) {
        //      sql문 작성
        String sql = "update player set team_id = null where id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, playerId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 선수 삭제
    public void delete() {
        //  sql문 작성
        String sql = "";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 선수 하나 찾기
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

    // 선수 전체 찾기
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
