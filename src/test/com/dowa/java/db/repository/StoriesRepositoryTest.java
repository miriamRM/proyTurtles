package com.dowa.java.db.repository;

import com.dowa.java.db.model.Stories;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class StoriesRepositoryTest extends TestCase {

    @Test
    public void testInsertStory() throws Exception {
        StoriesRepository repo = new StoriesRepository();
        Stories story = new Stories();
        story.setIdUser(1); //ya existe en la bd el user con id=1
        story.setStory("Esta es una nueva historia que quiero agregar");
        story.setIdTopic(1); //ya existe en la bd el topic con id=1
        repo.insertStory(story);
        assertNotNull(repo.findStoriesFromUser(1));
    }

    @Test
    public void testFindAllStories() throws Exception {
        StoriesRepository repo = new StoriesRepository();
        List <Stories> allStories = repo.findAllStories();
        assertEquals(1,allStories.size());

    }

    @Test
    public void testFindStoriesFromUser() throws Exception {
        int idUser = 1;
        StoriesRepository repo = new StoriesRepository();
        List<Stories> userStories = repo.findStoriesFromUser(idUser);
        assertEquals(1,userStories.size());
    }

    @Test
    public void testDeleteStory() throws Exception {
        StoriesRepository repo = new StoriesRepository();
        int idStory = 1;
        repo.deleteStory(idStory);
        assertEquals(0,repo.findStoriesFromUser(1).size());
        //assertNull(repo.findStoriesFromUser(1));
    }
}