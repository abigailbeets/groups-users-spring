package com.allstate.domain;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testGetNicknameForYear2000() throws Exception {

        User user = new User();
        user.setFirst("Zach");
        user.setLast("Quinn");
        user.setBirthday("01-01-2000");

        String expected = "Dope Zach";
        String actual = user.getNickname();

        assertEquals("nickname should be Dope Zach", expected, actual);
    }

    @Test
    public void testGetNicknameForYear1980() throws Exception {

        User user = new User();
        user.setFirst("Abigail");
        user.setLast("Beets");
        user.setBirthday("01-01-1980");

        String expected = "Rad Abigail";
        String actual = user.getNickname();

        assertEquals("nickname should be Rad Abigail", expected, actual);
    }

    @Test
    public void testGetNicknameForYear1930() throws Exception {

        User user = new User();
        user.setFirst("Abigail");
        user.setLast("Beets");
        user.setBirthday("01-01-1930");

        String expected = "Dapper Abigail";
        String actual = user.getNickname();

        assertEquals("nickname should be Dapper Abigail", expected, actual);
    }
}
