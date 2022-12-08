package com.gestor_reportes.springboot.app.domain;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Conection {
    
    String host = "10.191.209.185";
    String username = "svreptnf";
    String password = "Telcel01#";
    int port = 22;
    String command = "sh /u01/Telcel/DATA/GestorReportes/gestor_reportes.sh ";
    String remoteFile;
    String subComand;
    public Conection(String subComand, String remoteFile) {
        this.subComand = subComand;
        this.remoteFile = "/u01/Telcel/DATA/GestorReportes/".concat(remoteFile);
    }

    public void downloadFile(String source, String destination) throws JSchException, SftpException {
        //String source = "/u01/Telcel/REPORTS/BESGestorReports/done/00L/20220621/00L_RCMRC371_REPORTE_DE_AJUSTES_FACTURADOS_GSM_POR_CICLO_31175.20220621114744_FC14_06_2022_C14_R01_NoData.xlsx";
        //String destination = "C:\\Users\\jguerrero\\Downloads\\lalo.xlsx";
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        System.out.println("Connection established.");
        System.out.println("Crating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");
        sftpChannel.get(source, destination);
    }

    public ArrayList<String> downloadInfo() throws JSchException, SftpException{
        ArrayList<String> files = new ArrayList<String>();
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
        System.out.println("Connection established.");
        System.out.println("Crating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");

        InputStream inputStream = sftpChannel.get(remoteFile);

        try (Scanner scanner = new Scanner(new InputStreamReader(inputStream))) {
            while (scanner.hasNextLine()) {
                files.add(scanner.nextLine());
            }
        }
        return files;
    }

    public boolean exeSsh() throws Exception {
        Session session = null;
        ChannelExec channel = null;
        boolean response = true;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command.concat(subComand));
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.setErrStream(errorStream);
            channel.connect();
            while (channel.isConnected()) {
                Thread.sleep(100);
            }
            //response = new String(responseStream.toByteArray());
            String errorResponse = new String(errorStream.toByteArray());
            if (!errorResponse.isEmpty()) {
                //throw new Exception(errorResponse);
                response = false;
                System.out.println(errorResponse);
            }
        } finally {
            if (session != null)
                session.disconnect();
            if (channel != null)
                channel.disconnect();
        }
        
        return response;
    }


    public boolean exeSshWitchParam(String _subComand) throws Exception {
        Session session = null;
        ChannelExec channel = null;
        boolean response = true;
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command.concat(_subComand));
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.setErrStream(errorStream);
            channel.connect();
            while (channel.isConnected()) {
                Thread.sleep(100);
            }
            //response = new String(responseStream.toByteArray());
            String errorResponse = new String(errorStream.toByteArray());
            if (!errorResponse.isEmpty()) {
                //throw new Exception(errorResponse);
                response = false;
                System.out.println(errorResponse);
            }
        } finally {
            if (session != null)
                session.disconnect();
            if (channel != null)
                channel.disconnect();
        }
        
        return response;
    }

}
