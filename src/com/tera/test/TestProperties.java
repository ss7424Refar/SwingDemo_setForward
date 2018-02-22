package com.tera.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

    public static void main(String args[]) {
        Properties pro = new Properties();

        InputStream in = TestProperties.class.getClassLoader().getResourceAsStream("etc/ServerInfo.properties");
        try {
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(pro.getProperty("IP"));
        System.out.println(pro.getProperty("Domain"));
        System.out.println(System.getProperty("user.name"));

        System.out.println(System.getProperty("os.name"));
    }
}
