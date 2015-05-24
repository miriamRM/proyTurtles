package com.dowa.java.db.repository;

import com.dowa.java.db.model.Topics;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Created by rabanita on 24/05/15.
 */
public class TopicsRepositoryTest extends TestCase {

    @Test
    public void testInsertTopics() throws Exception {
        TopicsRepository repo = new TopicsRepository();
        Topics topic = new Topics();
        topic.setTopic("Trabajo");
        repo.insertTopics(topic);
        assertEquals(4,repo.findAllTopics().size());
    }

    @Test
    public void testFindAllTopics() throws Exception {
        TopicsRepository repo = new TopicsRepository();
        List<Topics> topics = repo.findAllTopics();
        assertEquals(4,topics.size());
    }

    @Test
    public void testUpdateTopic() throws Exception {
        TopicsRepository repo = new TopicsRepository();
        Topics topic = new Topics();
        topic.setIdTopic(2);
        topic.setTopic("TRABAJ");
        int updatedRow = repo.updateTopic(topic);
        assertEquals(4,updatedRow);
    }

    @Test
    public void testDeleteTopic() throws Exception {
        TopicsRepository repo = new TopicsRepository();
        int idTopic = 2;
        repo.deleteTopic(idTopic);
        assertEquals(4,repo.findAllTopics().size());
    }
}