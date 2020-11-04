package controllers;

import models.LoginModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class SignupServlet extends HttpServlet {
    private LoginModel loginModel;

    public SignupServlet() throws SQLException, ClassNotFoundException {
        super();
        this.loginModel = new LoginModel();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("userid");
        session.setAttribute("userid", username);
        String url = null;
        if(username==null || username.isEmpty()){
            url = "/signup.jsp";
        }
        try {
            this.loginModel.addUser(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        url = "/todolist.jsp";
        getServletContext().getRequestDispatcher(url).forward(request,response);
    }

}
