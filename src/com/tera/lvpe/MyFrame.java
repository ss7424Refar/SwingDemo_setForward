package com.tera.lvpe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

class MyFrame extends JFrame implements ItemListener {
    private JPanel jPanel;
    private JRadioButton radioButton1, radioButton2;
    private ButtonGroup buttonGroup;
    private JTextArea jTextArea;
    private Toolkit kit;
    private Dimension screenSize;

    private AboutHostsFile aboutHostsFile = new AboutHostsFile();
    private int hostsStatus;

    public MyFrame() throws Exception {

        jTextArea = new JTextArea(5, 20);
        jTextArea.setMargin(new Insets(5, 5, 5, 5));
        jTextArea.setEditable(false);

        try {
            hostsStatus = aboutHostsFile.getHostsStatus();
            if (hostsStatus == 204) {
                radioButton1 = new JRadioButton(TitleList.A_SELECT, true);
                radioButton2 = new JRadioButton(TitleList.B_SELECT);
                jTextArea.setText(TitleList.A_Info);
            } else if (hostsStatus == 404) {
                radioButton1 = new JRadioButton(TitleList.A_SELECT);
                radioButton2 = new JRadioButton(TitleList.B_SELECT, true);
                jTextArea.setText(TitleList.B_Info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        jPanel = new JPanel();
        buttonGroup = new ButtonGroup();

        kit = Toolkit.getDefaultToolkit();
        screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.setBorder(BorderFactory.createTitledBorder("Select Forward"));

        radioButton1.addItemListener(this);
        radioButton2.addItemListener(this);

        jPanel.add(radioButton1);
        jPanel.add(radioButton2);

        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

        this.add(jPanel, BorderLayout.NORTH);
        this.add(jTextArea, BorderLayout.CENTER);
        this.setTitle("Frame Title");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(250, 150);
        this.setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight()) / 2);
        this.setResizable(false);
        this.setVisible(true);


    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            try {
                if (aboutHostsFile.fileExist()) {
                    if (e.getSource() == radioButton1) {
                        jTextArea.setText("");
                        aboutHostsFile.deleteOrAddFile(false);
                        jTextArea.append(TitleList.A_Info);
                    } else if (e.getSource() == radioButton2) {
                        jTextArea.setText("");
                        aboutHostsFile.deleteOrAddFile(true);
                        jTextArea.append(TitleList.B_Info);
                    }
                } else {
                    jTextArea.setText("");
                    jTextArea.append(TitleList.C_Info);
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
}