package com.dowa.java.web;

import com.dowa.java.db.model.Stories;
import com.dowa.java.db.repository.StoriesRepository;
import com.dowa.java.db.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rabanita on 27/05/15.
 */
public class UsersProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String target = null;
        int page;

        if (req.getParameter("pag") == null){
            page = 0;
        }else {
            page = Integer.valueOf(req.getParameter("pag")) - 1;
        }

        if (req.getParameter("Uid") != null){
            int idUser = Integer.valueOf(req.getParameter("Uid"));
            UserRepository userRepo = new UserRepository();
            StoriesRepository storyRepo = new StoriesRepository();

            try {
                String userName = userRepo.findUserById(idUser).getUserName();
                req.setAttribute("userName", userName);
                int totRows = storyRepo.numRows(idUser);
                int numPag = totRows / 10; //10 num de hisorias que regresa la bd cada vez
                req.setAttribute("totPag", numPag);

                int offSet = getOffset(page);
                List<Stories> storiesFromUser = storyRepo.findStoriesFromUser(idUser, offSet);

                if (storiesFromUser.size() == 0){
                    req.setAttribute("noHistorias", "No hay historias, ¿porque no realizas una?");
                } else {
                    req.setAttribute("historias", storiesFromUser);
                    //List<User> allUsers = userRepo.findUserById();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            target = "/profile.jsp";
        } else{
            target = "/index.jsp";
        }

        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req,resp);
    }

    private int getOffset(int page){
        int offset = page * 10;
        return offset;
    }
}
