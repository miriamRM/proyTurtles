package com.dowa.java.web;

import com.dowa.java.db.model.User;
import com.dowa.java.db.repository.PruebaStudRepo;
import com.dowa.java.db.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by rabanita on 22/05/15.
 */
public class UsersLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //HttpSession session = req.getSession(false);
        String target = "/login.jsp";
        /*if (session != null){
            target = "/home.jsp";
            req.setAttribute("session", session.getId());
        }*/
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        RequestDispatcher requestDispatcher;
        String target = "/registry.jsp";

        if (session != null){
            req.setAttribute("session", session.getId());
        }

        UserRepository repo = new UserRepository();
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");
        try{
            User user = repo.findUserByMail(email);
            if(null != user.geteMail()){
                if (user.getPass().equals(pass)){
                    if (session != null){
                        req.setAttribute("name",user.getUserName());
                        session.setAttribute("userId", user.getIdUser());
                        target = "/home.jsp";
                    }
                } else{
                    req.setAttribute("malPass","Contraseña incorrecta");
                    target = "/login.jsp";
                }
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