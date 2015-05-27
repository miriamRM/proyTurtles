package com.dowa.java.web;

import com.dowa.java.db.model.Stories;
import com.dowa.java.db.model.Topics;
import com.dowa.java.db.repository.StoriesRepository;
import com.dowa.java.db.repository.TopicsRepository;
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
 * Created by rabanita on 26/05/15.
 */
public class StoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = null;
        RequestDispatcher requestDispatcher;
        if (req.getParameter("Hid") != null || req.getAttribute("idStory") != null) {
            int idStory = Integer.valueOf(req.getParameter("Hid"));
            StoriesRepository storyRepo = new StoriesRepository();
            TopicsRepository topicRepo = new TopicsRepository();
            UserRepository userRepo = new UserRepository();
            try {
                Stories story = storyRepo.findStoryById(idStory);
                req.setAttribute("historia", story);
                int idTopic = story.getIdTopic();
                int idUser = story.getIdUser();
                req.setAttribute("idUser",idUser);

                String topic = topicRepo.findTopicById(idTopic);
                req.setAttribute("tema", topic);

                String userName = userRepo.findUserById(idUser).getUserName();
                req.setAttribute("user", userName);

                int votes = story.getVotes();
                int percentUp;
                int percentDown;
                if (votes != 0) {
                    percentUp = story.getUp() * 100 / votes;
                    percentDown = story.getDown() * 100 / votes;
                } else{
                    percentUp = 0;
                    percentDown = 0;
                }
                req.setAttribute("contra", percentDown);
                req.setAttribute("favor", percentUp);
                target="story.jsp";

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            target = "index.jsp";
        }
        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        StoriesRepository storyRepo = new StoriesRepository();
        int idStory = Integer.valueOf(req.getParameter("Hid"));
        String target = "story.jsp";

        try{
            Stories story = storyRepo.findStoryById(idStory);
            if (req.getParameter("downButton") != null) {
                story.setDown(story.getDown() + 1);
                story.setVotes(story.getVotes() + 1);
                storyRepo.updateStory(story);
            } else if (req.getParameter("upButton") != null) {
                story.setUp(story.getUp() + 1);
                story.setVotes(story.getVotes() + 1);
                storyRepo.updateStory(story);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("idStory",idStory);
        requestDispatcher = req.getRequestDispatcher(target);
        requestDispatcher.forward(req, resp);
    }
}