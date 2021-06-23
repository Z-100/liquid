package classes;

import java.sql.*;

public class Conn {

    private ResultSet result;

    public void query(String statement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/liquid", "liquid", "liquid");

            Statement stmt = conn.createStatement();
            this.result = stmt.executeQuery(statement);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getResult() {
        return this.result;
    }
}
