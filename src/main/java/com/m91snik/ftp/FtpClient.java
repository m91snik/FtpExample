package com.m91snik.ftp;

import java.io.IOException;

/**
 * Created by m91snik on 25.05.14.
 */
public interface FtpClient {

    boolean uploadFile(String userName, String password, String hostName, String path, String fileName, byte[] file) throws IOException;

    byte[] downloadFile(String userName, String password, String hostName, String path, String fileName) throws IOException;
}
