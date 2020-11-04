package models;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateListModel extends HttpServlet {
    //public List<String> todolist;
    String jdbcurl = "jdbc:mysql://45.33.14.177/todo?serverTimezone=UTC";
    Class c= Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(jdbcurl, "dev", "devpass");

    public UpdateListModel() throws SQLException, ClassNotFoundException {
        super();
        //this.todolist = new ArrayList<>();
    }

    public List<String> getTasks(String user) throws SQLException {
        String username = user;
        List<String> usertasks = new ArrayList<>();
        Statement stmt1 = conn.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select text from todos where user_id = '"+ user +"'");
        while(rs1.next()){
             usertasks = Collections.singletonList(rs1.getString(1));
        }
        return usertasks;
    }
    public void updateActionItems(String item, String user) throws SQLException {
        String username = user;
        String itemname = item;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select id from todos where user_id = '"+ user +"' and text = '"+ item +"'");

        while(rs.next()){
            int id = rs.getInt(1);
            PreparedStatement ps1 = conn.prepareStatement("update todos set active = '"+ 0 +"' where id = '"+ id +"' and user_id = '"+ user +"' and text = '"+ item +"'");
            ps1.executeUpdate();
        }

    }
}
