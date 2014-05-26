package com.m91snik.ftp.impl;

import com.m91snik.ftp.FtpClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by m91snik on 25.05.14.
 */

/**
 * Very-very simple FtpClient example class which shows how to upload file using apache FtpClient
 */
public class FtpClientImpl implements FtpClient {

    @Override
    public boolean uploadFile(String userName, String password, String hostName, String path, String fileName, byte[] file) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostName);
            System.out.println("Connected to server.");
            System.out.print(ftpClient.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            int reply = ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new IOException("FTP server refused connection.");
            }
            ftpClient.login(userName, password);

            ftpClient.changeWorkingDirectory(path);
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                throw new IOException("path does not exist");
            }


            boolean result = ftpClient.storeFile(fileName, new ByteArrayInputStream(file));

            ftpClient.logout();

            return result;

        } finally {
            if(ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
        }
    }

    @Override
    public byte[] downloadFile(String userName, String password, String hostName, String path, String fileName)  throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(hostName);
            System.out.println("Connected to server.");
            System.out.print(ftpClient.getReplyString());

            // After connection attempt, you should check the reply code to verify
            // success.
            int reply = ftpClient.getReplyCode();

            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                throw new IOException("FTP server refused connection.");
            }
            ftpClient.login(userName, password);

            ftpClient.changeWorkingDirectory(path);
            if(!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())){
                throw new IOException("path does not exist");
            }


            byte[] bytes = IOUtils.toByteArray(ftpClient.retrieveFileStream(fileName));

            ftpClient.logout();

            return bytes;

        } finally {
            if(ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch(IOException ioe) {
                    // do nothing
                }
            }
        }
    }
}
