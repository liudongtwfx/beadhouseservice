package com.beadhouse.business.beadhousebusiness.LeisuregroupBusiness;

import com.beadhouse.System.LogType;
import com.beadhouse.business.filehandle.FileHandler;
import com.beadhouse.model.admin.LeisureGroupImageList;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class LeisuregroupBusiness {
    public Object handleLeisuregroupImagesUpload(HttpServletRequest request) {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            return false;
        }
        MultipartHttpServletRequest multirequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multirequest.getFiles("file_data");
        for (MultipartFile file : files) {
            try {
                FileHandler.save(file, LeisureGroupImageList.realPath);
            } catch (Exception e) {
                LogType.EXCETPION.getLOGGER().error(e);
                return false;
            }
        }
        return true;
    }
}
