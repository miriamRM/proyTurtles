package com.dowa.java.web;

import com.dowa.java.db.model.Stories;
import com.dowa.java.db.model.User;
import com.dowa.java.db.repository.StoriesRepository;
import com.dowa.java.db.repository.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rabanita on 25/05/15.
 */
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page;
        if (req.getParameter("pag") == null){
            page = 0;
        }else {
            page = Integer.valueOf(req.getParameter("pag")) - 1;
        }
        String target = "/index.jsp";
        RequestDispatcher requestDispatcher;
        StoriesRepository storyRepo = new StoriesRepository();
        UserRepository userRepo = new UserRepository();

        try {
            int totRows = storyRepo.numRows();
            int numPag = totRows / 10; //10 num de hisorias que regresa la bd cada vez
            req.setAttribute("totPag", numPag);

            int offSet = getOffset(page);
            List<Stories> allStories = storyRepo.findAllStories(offSet);

            if (allStories.size() == 0){
                req.setAttribute("noHistorias", "No hay historias, ¿porque no realizas una?");
            } else {
                req.setAttribute("historias", allStories);
                //List<User> allUsers = userRepo.findUserById();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req,resp);

        /*HttpSession session = req.getSession(false);

        if (session != null){
            try{
                int userId = (int) session.getAttribute("userId");
                UserRepository userRepo = new UserRepository();
                User user = userRepo.findUserById(userId);
                req.setAttribute("userName",user.getUserName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }

    private int getOffset(int page){
        int offset = page * 10;
        return offset;
    }
}
