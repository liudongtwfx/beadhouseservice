package com.liudong.test;

import com.liudong.DAO.User.ElderPeople.ElderPeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * Created by liudong on 2017/4/26.
 */

public class Main {
    @Autowired
    ElderPeopleRepository elderPeopleRepository;

    public static void main(String[] args) throws ParseException {
        String s;
        if ((s = "123").length() == 3) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
