package models;

import java.sql.*;
import java.lang.*;


public class LoginModel {
    String jdbcurl = "jdbc:mysql://45.33.14.177/todo?serverTimezone=UTC";


    public LoginModel() throws SQLException, ClassNotFoundException { }

     Class c = Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(jdbcurl, "dev", "devpass");


    public UserModel validateLogin(String username) throws SQLException {
        String user = username;
        PreparedStatement ps = conn.prepareStatement("select email from users where email = ?");
        ps.setString(1,user);
        ResultSet rs = ps.executeQuery();
            UserModel userm = null;
        if(rs.next()){
            userm = new UserModel();
            userm.setEmail(rs.getString("email"));

        //conn.close();
        }
        return userm;
    }
        public void addUser(String username) throws SQLException {
            String useremail = username;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select MAX(id) from users");
            while (rs.next()) {
                int id = rs.getInt(1);
                 id = id +1;
                PreparedStatement ps = conn.prepareStatement("insert into users(id, email) values('" + id + "','" + useremail + "')");
                ps.executeUpdate();
                //conn.close();
            }
        }
    }

