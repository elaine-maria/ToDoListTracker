package controllers;

import models.LoginModel;
import models.ToDoListModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ToDoListServlet extends HttpServlet {
    private ToDoListModel model;

    public ToDoListServlet() throws SQLException, ClassNotFoundException {
        super();
        this.model = new ToDoListModel();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String itemname = request.getParameter("item");

        String user = (String) session.getAttribute("username");
        String url = null;

        if (request.getParameter("submit") != null) {
            try {
                this.model.addActionItems(user, itemname);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.setAttribute("actionitemlist", this.model.todolist);
            url = "/todolist.jsp";
        } else {

            if (request.getParameter("status") != null) {
                url = "/updatelist.jsp";
            }
        }
            getServletContext()
                    .getRequestDispatcher(url)
                    .forward(request, response);

    }
}
