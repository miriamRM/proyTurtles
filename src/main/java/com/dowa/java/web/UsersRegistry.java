package com.dowa.java.web;

import com.dowa.java.db.model.PruebaStudent;
import com.dowa.java.db.model.User;
import com.dowa.java.db.repository.PruebaStudRepo;
import com.dowa.java.db.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by rabanita on 25/05/15.
 */
public class UsersRegistry  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registry.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String target = "/registry.jsp";
        UserRepository repo = new UserRepository();
        User user = new User();
        String name = req.getParameter("userName");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        try {
            if (repo.usernameUsed(name)) {
                req.setAttribute("nombreUsado", "El Nombre de usuario ha sido usado, por favor elige otro.");
                target = "/registry.jsp";
            } else if (repo.findUserByMail(email).geteMail().equals(email)) {
                req.setAttribute("emailUsado", "Este correo ya ha sido usado, por favor usa otro");
                target = "/registry.jsp";
            } else {
                user.setUserName(name);
                user.seteMail(email);
                user.setPass(pwd);
                repo.insertUser(user);
                if (repo.findUserByMail(email).geteMail() != null) {
                    req.setAttribute("bien", "Registro correcto!");
                } else {
                    req.setAttribute("message", "No se encuentra en la base de datos, por favor registrese ");
                }
                target = "/login.jsp";
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req,resp);
    }
}
