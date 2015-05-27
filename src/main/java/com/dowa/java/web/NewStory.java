package com.dowa.java.web;

import com.dowa.java.db.model.Stories;
import com.dowa.java.db.model.Topics;
import com.dowa.java.db.repository.StoriesRepository;
import com.dowa.java.db.repository.TopicsRepository;

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
 * Created by rabanita on 27/05/15.
 */
public class NewStory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String target = "/newStory.jsp";
        TopicsRepository topicsRepo = new TopicsRepository();

        try{
            List<Topics> topics = topicsRepo.findAllTopics();
            req.setAttribute("temas", topics);
            req.setAttribute("selec", topics.get(0));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        String target = "/story.jsp";
        HttpSession session = req.getSession(false);
        //if (session != null) {
            //int idUser = (Integer) session.getAttribute("userId");

            Stories story = new Stories();
            StoriesRepository storysRepo = new StoriesRepository();
            String historia = req.getParameter("story");
            String tema = req.getParameter("tema");

            try {
                if (historia == null) {
                    req.setAttribute("histVacio", "Anda no seas tímido, cuentanos tu historia, no lo dejes vacío");
                } else if (tema == null) {
                    req.setAttribute("histVacio", "Elige el tema al que se relaciona tu historia");
                } else {
                    story.setIdUser(3);
                    story.setStory(historia);
                    story.setIdTopic(Integer.valueOf(tema));
                    storysRepo.insertStory(story);

                    int idStory = storysRepo.getLastRecord();
                    req.setAttribute("idStory",idStory);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        /*} else{
            req.setAttribute("noLogin","No puedes crear una historia porque no has iniciado sesi&oacute;n da click ");
        }*/

        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req, resp);
    }
}
