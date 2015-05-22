package com.dowa.java.db.repository;

import com.dowa.java.db.model.Comments;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class CommentsRepositoryTest extends TestCase {

    @Test
    public void testInsertComment() throws Exception {
        CommentsRepository repo = new CommentsRepository();
        Comments comment = new Comments();
        comment.setIdStory(10);
        comment.setIdUser(1);
        comment.setComment("Comentario por el usuario con id=1 a la historia con id=10");
        repo.insertComment(comment);
        assertNotNull(repo.findCommentsOfStory(10));
    }

    @Test
    public void testFindCommentsOfStory() throws Exception {
        int idStory = 10;
        CommentsRepository repo = new CommentsRepository();
        List<Comments> storyComments = repo.findCommentsOfStory(idStory);
        assertEquals(1,storyComments.size());
    }

    @Test
    public void testDeleteComment() throws Exception {
        CommentsRepository repo = new CommentsRepository();
        int idComment = 1;
        repo.deleteComment(idComment);
        assertEquals(0,repo.findCommentsOfStory(10).size());
    }
}