package com.liudong.business.filehandle;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liudong on 17-6-19.
 */
public class FileHandler {
    public static final String slash;

    static {
        if (System.getProperty("os.name").contains("Window")) {
            slash = "\\";
        } else {
            slash = "/";
        }
    }

    public static String save(MultipartFile file, String des) throws IOException {
        if (file == null) {
            return "";
        }
        File imagefolder = new File(des);
        if (!imagefolder.exists()) {
            imagefolder.mkdir();
        }
        String[] filenamesplit = file.getOriginalFilename().split("\\.");
        String newFileName = getRandomName() + "." + filenamesplit[filenamesplit.length - 1];
        File newImageFile = new File(imagefolder + slash + newFileName);
        while (newImageFile.exists()) {
            newFileName = getRandomName() + "." + filenamesplit[filenamesplit.length - 1];
            newImageFile = new File(imagefolder + slash + newFileName);
        }
        try {
            newImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(newImageFile.getAbsolutePath());
        InputStream in = file.getInputStream();
        FileOutputStream out = new FileOutputStream(newImageFile);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        in.close();
        out.flush();
        out.close();
        return newFileName;
    }

    public static String getRandomName() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(base.charAt((int) (Math.random() * base.length())));
        }
        return sb.toString();
    }

    public static boolean deleteFile(String des) {
        File file = new File(des);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }
}
