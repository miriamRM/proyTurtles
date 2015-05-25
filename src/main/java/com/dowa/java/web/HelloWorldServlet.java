package com.dowa.java.web;

import com.dowa.java.db.model.PruebaStudent;
import com.dowa.java.db.repository.PruebaStudRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by rabanita on 17/05/15.
 */
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registry.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        //String target = "";
        PruebaStudRepo repo = new PruebaStudRepo();
        PruebaStudent student = new PruebaStudent();
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        try {
            student.setFirstName(name);
            student.setLastName(lastName);
            repo.insertStudent(student);
            if (repo.findStudentByFirstName(name).getFirstName() != null) {
                req.setAttribute("bien", "Registro correcto!");
            } else {
                req.setAttribute("message", "No se encuentra en la base de datos, por favor registrese ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        requestDispatcher = req.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req, resp);

    }
}
