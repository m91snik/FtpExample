package com.m91snik.ftp;

import com.m91snik.ftp.impl.FtpClientImpl;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by m91snik on 26.05.14.
 */
public class FtpClientTest {


    public static final String USER_NAME = "username";
    public static final String PASSWORD = "password";
    public static final String HOST_NAME = "hostname";
    public static final String PATH = "path";
    public static final String FILE_NAME = "fileName";

    @Test
    public void testUploadFile() throws Exception {
        FtpClient ftpClient=new FtpClientImpl();
        URL response = this.getClass().getResource(File.separator+FILE_NAME);
        File file = new File(response.getFile());
        byte[] content= FileUtils.readFileToByteArray(file);
        Assert.assertTrue(ftpClient.uploadFile(USER_NAME, PASSWORD, HOST_NAME, PATH, file.getName(), content));
    }

    @Test
    public void testDownloadFile() throws Exception {
        FtpClient ftpClient=new FtpClientImpl();
        Assert.assertNotNull(ftpClient.downloadFile(USER_NAME, PASSWORD, HOST_NAME, PATH, FILE_NAME));
    }

    @Test
    public void testUploadAndDownloadFile() throws Exception {
        FtpClient ftpClient=new FtpClientImpl();
        URL response = this.getClass().getResource(File.separator+FILE_NAME);
        File file = new File(response.getFile());
        byte[] content= FileUtils.readFileToByteArray(file);
        Assert.assertTrue(ftpClient.uploadFile(USER_NAME,PASSWORD, HOST_NAME,PATH, file.getName(),content));

        byte[] downloadedFile = ftpClient.downloadFile(USER_NAME, PASSWORD, HOST_NAME, PATH, file.getName());
        Assert.assertTrue(Arrays.equals(content, downloadedFile));
    }
}
