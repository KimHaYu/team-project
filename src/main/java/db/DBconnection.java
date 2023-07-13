package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
    public static Connection getInstance() {
//       MySQL 연결 정보
        String url = "jdbc:mysql://localhost:3306/baseball_db"; // TODO 바꾸기
//       프로토콜 이름
        String username = "root";
        String password = "root1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("DB 성공");
            return connection;
        } catch (Exception e) {
            System.out.println("DB 실패" + e.getMessage());
        }

        return null;
    }

}
