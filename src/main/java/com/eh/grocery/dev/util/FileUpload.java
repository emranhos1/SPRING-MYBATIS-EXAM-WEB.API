package com.eh.grocery.dev.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.springframework.stereotype.Component;

@Component
public class FileUpload {
    public String fileUpload(Part part) throws IOException {

        String uploadedFilePath = "/static/images";
        System.out.println(uploadedFilePath);
        File newFile = new File(uploadedFilePath);
        String fileName = getFileName(part);
        File file = new File(uploadedFilePath + fileName);
        
        if (file.exists()) {
            UUID count = UUID.randomUUID();
            File file1 = new File(uploadedFilePath + count + fileName);
            String newFileName = count + "_" + fileName;

            newFile.mkdir();
            OutputStream out1 = null;
            out1 = new FileOutputStream(new File(uploadedFilePath + File.separator + newFileName));
            InputStream fileContent = part.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out1.write(bytes, 0, read);
            }
            return newFileName;
        } else {
            newFile.mkdir();
            OutputStream out1 = null;
            out1 = new FileOutputStream(new File(uploadedFilePath + File.separator + fileName));
            InputStream fileContent = part.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out1.write(bytes, 0, read);
            }
            return fileName;
        }
    }

    public static String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    public boolean fileDelete(String fileName) {

    	String uploadedFilePath = "/static/images";
//        String uploadedFilePath = request.getContextPath()+ "/allImage/";
//        String uploadedFilePath = "E:/Programming/1. Office project/Project/NewParaparMaven/allImage/";
        System.out.println(uploadedFilePath);
        
        File file = new File(uploadedFilePath + fileName);
        System.out.println(uploadedFilePath + fileName);
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
            return true;
        } else {
            System.out.println("Delete operation is failed.");
            return false;
        }
    }
}
