package classes;

import java.sql.*;

public class Conn {

    private ResultSet result;

    public void query(String statement, int queryMode) { // TODO Make Query mode work
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/liquid", "liquid", "liquid");

            Statement stmt = conn.createStatement();

            switch (queryMode) {
                //    0 = SELECT, 1 = INSERT or UPDATE
                case 0 -> this.result = stmt.executeQuery(statement);
                case 1 -> stmt.executeUpdate(statement);
            }

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error in SQL Statement");
        }
    }

    public ResultSet getResult() {
        return this.result;
    }
}
