package classes;

import javax.xml.transform.Result;
import java.sql.*;

public class Conn {

    private ResultSet result;

    public void conn(String statement) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/liquid","root","");

            Statement stmt = conn.createStatement();
            this.result = stmt.executeQuery(statement);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getResult() {
        return this.result;
    }
}
