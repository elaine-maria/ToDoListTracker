package controllers;

import models.LoginModel;
import models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private LoginModel loginModel;

    public LoginServlet() throws SQLException, ClassNotFoundException {
        super();
        this.loginModel = new LoginModel();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String url = null;

        if(username==null || username.isEmpty()){
            url = "/index.jsp";
        }
        try {
           UserModel um = this.loginModel.validateLogin(username);

           if(um != null){
               session.setAttribute("username", username);
               url = "/todolist.jsp";
           }
           else{
               session.setAttribute("username", username);
               url = "/signup.jsp";
           }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }
}
