package com.example.myapplication.data;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;

public class SSHConnection {

    public static void executeRemoteCommand (String username, String password, String hostname, int port) throws Exception {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, hostname, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            System.out.println("Connection established.");
            System.out.println("Crating SFTP Channel.");
            // SSH Channel
            ChannelExec channelssh = (ChannelExec)
                    session.openChannel("exec");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            channelssh.setOutputStream(baos);

            // Execute command
            channelssh.setCommand("cd Desktop && python3 blink.py");
            channelssh.connect();
            channelssh.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}

