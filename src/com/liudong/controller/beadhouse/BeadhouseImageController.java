package com.liudong.controller.beadhouse;

import com.liudong.DAO.BeadHouse.BeadhouseImageManageRepository;
import com.liudong.model.Beadhouse.BeadhouseImageManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudong on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/beadhouse/imagemanage")
public class BeadhouseImageController {
    @Inject
    BeadhouseImageManageRepository imageManageRepository;

    private static String imagePath = "http://localhost:8088/imagefiles/beadhouseimagefiles";
    private static String realPath = "E:\\Users\\liudong\\IdeaProjects\\login\\web\\WEB-INF\\imagefiles\\beadhouseimagefiles";

    @RequestMapping(value = "uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public Boolean upload(HttpServletRequest request) throws IOException {
        if (request.getParameter("upload") == null) return false;
        BeadhouseImageManage imageManage = new BeadhouseImageManage();
        handleImageRequest(request, imageManage);
        return this.imageManageRepository.save(imageManage) != null;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateImage(HttpServletRequest request) throws IOException {
        String imageId = request.getParameter("imageId");
        if (imageId == null || imageId.length() == 0) return false;
        BeadhouseImageManage imageManage = this.imageManageRepository.findOne(Long.parseLong(imageId));
        if (imageManage == null) return false;
        deleteImage(imageManage);
        handleImageRequest(request, imageManage);
        return this.imageManageRepository.save(imageManage) != null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public Boolean deleteImage(HttpServletRequest request) throws IOException {
        String imageId = request.getParameter("imageId");
        if (imageId == null || imageId.length() == 0) return false;
        BeadhouseImageManage imageManage = this.imageManageRepository.findOne(Long.parseLong(imageId));
        if (imageManage == null) return false;
        deleteImage(imageManage);
        this.imageManageRepository.delete(imageManage.getId());
        return true;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getImageList(HttpServletRequest request) {
        String beadhouseId = String.valueOf(request.getSession().getAttribute("beadhouseId"));
        if (beadhouseId == null || beadhouseId.length() == 0) return new HashMap<>();
        List<BeadhouseImageManage> imageManageList = this.imageManageRepository.findByBeadhouseid(Integer.parseInt(beadhouseId));
        Map<String, Object> values = new HashMap<>();
        values.put("imageManageList", imageManageList);
        values.put("imagePath", imagePath + "/" + beadhouseId);
        return values;
    }

    private void handleImageRequest(HttpServletRequest request, BeadhouseImageManage imageManage) throws IOException {
        imageManage.setImagePriority(Integer.parseInt(request.getParameter("priority")));
        imageManage.setBeadhouseid((Integer) request.getSession().getAttribute("beadhouseId"));
        MultipartHttpServletRequest multirequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> files = multirequest.getFileMap();
        for (String s : files.keySet()) {
            System.out.println("String : " + s + " MultipartFile: " + files.get(s).getOriginalFilename());
        }
        MultipartFile file = multirequest.getFile("upload");
        if (file == null) {
            return;
        }
        File imagefolder = new File(realPath + "\\" + request.getSession().getAttribute("beadhouseId"));
        if (!imagefolder.exists()) {
            imagefolder.mkdir();
        }
        String[] filenamesplit = file.getOriginalFilename().split("\\.");
        String newFileName = getRandomName() + "." + filenamesplit[filenamesplit.length - 1];
        File newImageFile = new File(imagefolder + "\\" + newFileName);
        while (newImageFile.exists()) {
            newFileName = getRandomName() + "." + filenamesplit[filenamesplit.length - 1];
            newImageFile = new File(imagefolder + "\\" + newFileName);
        }
        try {
            newImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        imageManage.setImagePath(newFileName);
        byte[] change = request.getParameter("description").getBytes("iso-8859-1");
        imageManage.setImageDescription(new String(change, "utf-8"));
    }

    private String getRandomName() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(base.charAt((int) (Math.random() * base.length())));
        }
        return sb.toString();
    }

    private void deleteImage(BeadhouseImageManage imageManage) {
        String deleteFilePath = realPath + "\\" + imageManage.getBeadhouseid() + "\\" + imageManage.getImagePath();
        System.out.println(deleteFilePath);
        File file = new File(deleteFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
