package com.beadhouse.business.beadhousebusiness.LeisuregroupBusiness;

import com.beadhouse.DAO.Admin.LeisureGroupRepository;
import com.beadhouse.System.LeisureGroupStatus;
import com.beadhouse.System.LogType;
import com.beadhouse.business.filehandle.FileHandler;
import com.beadhouse.model.admin.LeisureGroup;
import com.beadhouse.model.admin.LeisureGroupImageList;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class LeisuregroupBusiness {
    @Inject
    LeisureGroupRepository leisureGroupRepository;

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

    @Transactional
    public Object addLeisureGroup(HttpServletRequest request) {
        LeisureGroup group = new LeisureGroup();
        String title = request.getParameter("title");
        String details = request.getParameter("details");
        group.setDetails(details);
        group.setTitle(title);
        group.setGroupStatus(LeisureGroupStatus.NOTSTARTED.getStatus());
        group.setAddtime(new Date());
        try {
            this.leisureGroupRepository.save(group);
            return true;
        } catch (Exception e) {
            LogType.EXCETPION.getLOGGER().error(e.getMessage());
            return false;
        }
    }

    @Transactional
    public Object getLeisureGroup(int page, int size) {
        return null;
    }
}
