package com.revature.ers.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import com.revature.ers.dao.UserDao;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.ers.models.Role;
import com.revature.ers.models.User;

public class UserServiceTest {

    private static UserDao userDAO;

    private User GENERIC_EMPLOYEE_1;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        userDAO = mock(UserDao.class);

    }

    @Before
    public void setUp() throws Exception {
        GENERIC_EMPLOYEE_1 = new User(1,"genericEmployee1", "genericPassword", "first", "last", "email", Role.EMPLOYEE);
    }

    @Test
    public void testGetByUsernamePassesWhenUsernameExists() {
        when(userDAO.getUserByUserName(anyString())).thenReturn(GENERIC_EMPLOYEE_1);

        assertEquals(GENERIC_EMPLOYEE_1, userDAO.getUserByUserName(GENERIC_EMPLOYEE_1.getUsername()));

        verify(userDAO).getUserByUserName(GENERIC_EMPLOYEE_1.getUsername());
    }
}