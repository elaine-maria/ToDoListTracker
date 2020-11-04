package models;

import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoListModel extends HttpServlet {
    public List<String> todolist;
    String jdbcurl = "jdbc:mysql://45.33.14.177/todo?serverTimezone=UTC";

    public ToDoListModel() throws SQLException, ClassNotFoundException {
        super();
        this.todolist = new ArrayList<>();
    }
    Class c = Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(jdbcurl, "dev", "devpass");

    public void addActionItems(String user, String item) throws SQLException {

        this.todolist.add(item);
        String todoitem = item;
        String username = user;
        int itemstatus = 1;
        int todoid = 0;

        Statement stmt1 = conn.createStatement();
        ResultSet rs1 = stmt1.executeQuery("select MAX(id) from todos");
        while(rs1.next()) {
            todoid = rs1.getInt(1);
            todoid = todoid + 1;
        }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select id from users where email = '" + username + "'");


            while (rs.next()) {
                int user_id = rs.getInt(1);

                PreparedStatement ps = conn.prepareStatement("insert into todos(id,user_id,text,active) values( '" + todoid + "' ,'" + user_id + "','" + todoitem + "','" + 1 + "')");
                ps.executeUpdate();
            }
    }
    public void updateActionItems(String username, String itemname) throws SQLException {
        String user = username;
        String item = itemname;

        Statement stmt2 = conn.createStatement();
        ResultSet rs2 = stmt2.executeQuery("select id from todos where user_id = '"+ user +"' and text = '"+ item +"'");

        while(rs2.next()){
            int id = rs2.getInt(1);
            PreparedStatement ps1 = conn.prepareStatement("update todos set active = '"+ 0 +"' where id = '"+ id +"' and user_id = '"+ user +"' and text = '"+ item +"'");
            ps1.executeUpdate();
        }

    }

}
