package com.liudong.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;

/**
 * Created by liudong on 2017/4/26.
 */

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws ParseException {
        String s;
        LOGGER.error("this is class main");
        if ((s = "123").length() == 3) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
