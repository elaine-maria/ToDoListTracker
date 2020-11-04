package controllers;

import models.ToDoListModel;
import models.UpdateListModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateListServlet extends HttpServlet {

    private UpdateListModel model;

    public UpdateListServlet() throws SQLException, ClassNotFoundException {
        super();
        this.model = new UpdateListModel();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        String itemtask = (String) request.getAttribute("tasks");


        try {
            List<String> listitem = this.model.getTasks(user);
            session.setAttribute("actionitem", listitem);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            this.model.updateActionItems(itemtask, user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            String url = "/todolist.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

}
