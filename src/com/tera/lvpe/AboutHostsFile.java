package com.tera.lvpe;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class AboutHostsFile {
    private static final String OS_NAME = "Linux";
    private String NEXT_LINE;
    private String ip;
    private String domain;
    private String os_name;
    private String filePath;
    private String findKey;
    private File file;
    private boolean isExistKey;

    private ArrayList<String> arrayList = new ArrayList<>();

    public AboutHostsFile() throws Exception {
        Properties properties = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "/ServerInfo.properties"));
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ip = properties.getProperty("IP");
        domain = properties.getProperty("Domain");

        os_name = System.getProperty("os.name");
        NEXT_LINE = System.getProperty("line.separator");
        findKey = properties.getProperty("findKey");

        System.out.println();
        if (OS_NAME.equalsIgnoreCase(os_name)) {
            filePath = "/etc/hosts";
        } else {
            filePath = properties.getProperty("windowsHostsPath");
        }
        filePermissionSet();
    }

    public int getHostsStatus() throws IOException {
        readSaveFile();
        if (isExistKey) {
            for (String list : arrayList) {
                if (list.startsWith("#") && list.contains(findKey)) {
                    return 404;
                }
            }
            return 204;
        }
        return 404;
    }

    public boolean fileExist() {
        file = new File(filePath);
        return file.exists();
    }

    public void filePermissionSet() throws Exception {
        if (fileExist()) {
            if (OS_NAME.equalsIgnoreCase(os_name)) {
                Runtime.getRuntime().exec("sudo chmod 777 " + filePath);
            } else {
                file.setWritable(true);
            }
        }
    }

    public ArrayList<String> readSaveFile() throws IOException {
        arrayList = new ArrayList<>();
        if (fileExist()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                arrayList.add(line);

                if (line.indexOf(findKey) != -1) {
                    isExistKey = true;
                }
            }
            bufferedReader.close();
            return arrayList;
        }
        return null;
    }

    public void deleteOrAddFile(boolean deleteFlag) {
        try {
            readSaveFile();
            if (null != arrayList) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).contains(findKey)) {
                        arrayList.remove(i);
                        i--;
                    }
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                for (String list : arrayList) {
                    bw.write(list + NEXT_LINE);
                }
                if (!deleteFlag) {
                    bw.write(ip + "  " + domain + NEXT_LINE);
                }

                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}