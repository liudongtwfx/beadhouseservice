package main.java.com.beadhouse.controller.beadhouse;

import main.java.com.beadhouse.DAO.BeadHouse.BeadhouseImageManageRepository;
import main.java.com.beadhouse.business.filehandle.FileHandler;
import main.java.com.beadhouse.model.beadhouse.BeadhouseImageManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by beadhouse on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/beadhouse/imagemanage")
public class BeadhouseImageController {
    public static final String slash;

    static {
        if (System.getProperty("os.name").contains("Window")) {
            slash = "\\";
        } else {
            slash = "/";
        }
    }

    @Inject
    BeadhouseImageManageRepository imageManageRepository;

    @RequestMapping(value = "uploadimage", method = RequestMethod.POST)
    @ResponseBody
    public Boolean upload(HttpServletRequest request) throws IOException {
        //if (request.getParameter("upload") == null) return false;
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
        values.put("imagePath", BeadhouseImageManage.imageUrl + "/" + beadhouseId);
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
        String des = BeadhouseImageManage.realPath + slash + request.getSession().getAttribute("beadhouseId");
        String newFileName = FileHandler.save(file, des);
        imageManage.setImagePath(newFileName);
        byte[] change = request.getParameter("description").getBytes("iso-8859-1");
        imageManage.setImageDescription(new String(change, "utf-8"));
    }

    private void deleteImage(BeadhouseImageManage imageManage) {
        String deleteFilePath = BeadhouseImageManage.realPath + slash + imageManage.getBeadhouseid() + slash + imageManage.getImagePath();
        FileHandler.deleteFile(deleteFilePath);
    }
}
