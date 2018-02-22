package com.tera.lvpe;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("hello");
        Font font = new Font("simsun", Font.PLAIN, 11);
        UIManager.put("RadioButton.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TitledBorder.font", font);

        MyFrame myFrame = new MyFrame();
    }
}

