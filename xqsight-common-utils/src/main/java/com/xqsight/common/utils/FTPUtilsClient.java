/**
 * Project:pmpf-common-utils
 * File:FTPUtils.java
 * Copyright 2004-2015 All rights reserved.
 */
package com.xqsight.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

/**
 * @author zengwen
 * @author xqsight-jerry
 * @date 2015年5月25日
 * @version 修改为需要实例化的类
 */
public class FTPUtilsClient {
    
    private Log logger = LogFactory.getLog(getClass());

    private FTPClient ftpClient = new FTPClient();;
    private String    ip;                         // 服务器IP地址
    private String    userName;                   // 用户名
    private String    userPwd;                    // 密码
    private int       port;                       // 端口号

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @SuppressWarnings("unused")
    private FTPUtilsClient() {

    }

    public FTPUtilsClient(String ip, int port, String userName, String userPwd) {
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public boolean isConnected() {
        return ftpClient.isConnected();
    }

    /**
     * @param ip
     * @param port
     * @param userName
     * @param userPwd
     * @throws SocketException
     * @throws IOException
     *             function:连接到服务器
     */
    public void connectServer() {
        if (!ftpClient.isConnected()) {
            connectServer(ip, port, userName, userPwd, null);
        }
    }

    /**
     * @param ip
     * @param port
     * @param userName
     * @param userPwd
     * @param path
     * @throws SocketException
     * @throws IOException
     *             function:连接到服务器
     */
    public void connectServer(String path) {
        if (!ftpClient.isConnected()) {
            connectServer(ip, port, userName, userPwd, path);
        }
    }

    /**
     * @param ip
     * @param port
     * @param userName
     * @param userPwd
     * @param path
     * @throws SocketException
     * @throws IOException
     *             function:连接到服务器
     */
    private void connectServer(String ip, int port, String userName, String userPwd, String path) {
        try {
            // 连接
            ftpClient.connect(ip, port);
            // 登录
            ftpClient.login(userName, userPwd);
            if (path != null && path.length() > 0) {
                // 跳转到指定目录
                ftpClient.changeWorkingDirectory(path);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws IOException
     *             function:关闭连接
     */
    public void closeServer() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从服务器上读取指定的zip文件
     * @param filePath
     * @param name
     * @param strencoding
     * @return
     * @throws ParseException
     */
    public List<String> readZipFile(String name, String filePath, String strencoding) throws ParseException {
        if (!ftpClient.isConnected()) {
            connectServer(ip, port, userName, userPwd, null);
        }
        List<String> list = new ArrayList<String>();
        InputStream ins = null;
        try {
            // 从服务器上读取指定的文件
            ftpClient.enterLocalPassiveMode(); // 提醒服务端打开端口
            ins = ftpClient.retrieveFileStream(name);
            if (ins == null) {
                logger.info("在文件夹 " + filePath + " 中找不到名为 " + name + " 的文件");
                return list;
            }
            ZipInputStream zin = new ZipInputStream(ins);
            ZipEntry ze;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(zin, strencoding));
                    String line;
                    while ((line = br.readLine()) != null) {
                        list.add(line);
                    }
                    // br.close();
                }
            }
            zin.closeEntry();
            if (ins != null) {
                ins.close();
            }
            // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
            ftpClient.getReply();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param fileName
     *            function:删除文件
     */
    public void deleteFile(String fileName) {
        if (!ftpClient.isConnected()) {
            connectServer(ip, port, userName, userPwd, null);
        }
        try {
            ftpClient.deleteFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        // FTPUtils ftp = new FTPUtils();
        // List<String> str = ftp.readFile();
        // System.out.println(str);
    }
}
