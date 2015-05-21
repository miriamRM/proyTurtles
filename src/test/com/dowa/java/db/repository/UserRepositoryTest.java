package com.dowa.java.db.repository;

import com.dowa.java.db.model.User;
import junit.framework.TestCase;
import org.junit.Test;

public class UserRepositoryTest extends TestCase {

    @Test
    public void testInsertUser() throws Exception {
        UserRepository rep = new UserRepository();
        User user = new User();
        user.setUserName("Rabani");
        user.seteMail("a327306@uabc.edu.mx");
        user.setPass("pass");
        rep.insertUser(user);
        assertNotNull(rep.findUserByMail(user.geteMail()));
    }

    @Test
    public void testFindUserByMail() throws Exception {
        String mail = "a327306@uabc.edu.mx";
        UserRepository rep = new UserRepository();
        User user = rep.findUserByMail(mail);
        assertEquals(mail, user.geteMail());
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserRepository rep = new UserRepository();
        User user = new User();
        user.setUserName("Rabanita");
        user.setPass("pass");
        int row = rep.updateUser(user);
        assertNotSame(0,row);
    }

    @Test
    public void testDeleteUser() throws Exception {
        UserRepository rep = new UserRepository();
        String mail = "a327306@uabc.edu.mx";
        rep.deleteUser(mail);
        assertNull(rep.findUserByMail(mail).getUserName());
    }
}