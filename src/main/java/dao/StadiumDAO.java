package dao;

import model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//야구장 테이블
/*
    DAO -> DB에 접근해서 SQL을 보내고 작업을 수행.
        ->
 */
public class StadiumDAO {
    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    // 야구장 삽입
    public int insert(String stadiumName) {
        // sql문 작성
        String sql = "insert into stadium(name, created_at) values(?, now())";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, stadiumName);
            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 야구장 수정
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

    // 야구장 삭제
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

    // 야구장 하나 찾기
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

    public List<Stadium> selectAll() {
        List<Stadium> stadiumList = new ArrayList<>();

        String sql = "select * from stadium";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                stadiumList.add(new Stadium(id, name, createdAt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stadiumList;
    }

    // 야구장 전체 찾기
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
