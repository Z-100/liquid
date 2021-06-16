package classes;

import java.sql.*;

public class Conn {
    public static void conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/liquid","root","");

            loginQuery(conn);

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    private static void loginQuery(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM user");

        while(result.next()) {
            System.out.println(result.getString("accountname") + "\t" + result.getString("password"));
        }
    }
}
