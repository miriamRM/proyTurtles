package com.dowa.java.web;

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
public class StudentLoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String target = "/index.jsp";
        PruebaStudRepo repo = new PruebaStudRepo();
        String name = req.getParameter("name");
        try{
            if(null != repo.findStudentByFirstName(name).getFirstName()){
                req.setAttribute("name",name);
                target = "/home.jsp";
            } else{
                req.setAttribute("message","No se encuentra en la base de datos, por favor registrese ");
                target = "/login.jsp";
            }
        }catch (SQLException e){
            System.err.println("SQLException" + e.getMessage());
        }
        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req,resp);
    }
}
