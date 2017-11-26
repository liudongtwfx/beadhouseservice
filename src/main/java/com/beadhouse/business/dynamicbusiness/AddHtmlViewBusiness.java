package main.java.com.beadhouse.business.dynamicbusiness;

import main.java.com.beadhouse.DAO.Admin.HtmlViewRepository;
import main.java.com.beadhouse.System.CommonFinalVariable;
import main.java.com.beadhouse.System.LogType;
import main.java.com.beadhouse.model.admin.HtmlView;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Date;

@Service
public class AddHtmlViewBusiness {
    @Inject
    HtmlViewRepository viewRepository;

    @Transactional
    public void addnewHtml(HtmlView view) throws IOException {
        view.setUpdateTime(new Date());
        String englishName = view.getEnglishName();
        String url = englishName.replaceAll("\\s+", "/");
        String filename = englishName.replaceAll("\\s+", "_");
        view.setUrl("/dynamic/" + url);
        view.setFilepath(filename);
        String absolute = CommonFinalVariable.ABSOLUTE_FILE_PATH + view.getFilepath() + ".html";
        File htmlFile = new File(absolute);
        if (htmlFile.exists()) {
            throw new FileAlreadyExistsException(htmlFile.getName());
        }
        this.viewRepository.save(view);
        createFile(view, htmlFile);
    }

    private void createFile(HtmlView view, File htmlFile) throws IOException {
        LogType.DEBUGINFO.getLOGGER().debug(htmlFile.getAbsolutePath());
        htmlFile.createNewFile();
        Document document = new Document(htmlFile.getAbsolutePath());
        document.append("<!DOCTYPE html>");

        Element meta = new Element("meta");
        meta.attr("http-equiv", "Content-Type");
        meta.attr("content", "text/html; charset=utf-8");
        Element title = new Element("title");
        title.text(view.getChineseTitle());

        Element head = new Element("head");
        head.appendChild(meta);
        head.appendChild(title);
        Element body = new Element("body");
        Element html = new Element("html");
        html.attr("xmlns:th", "http://www.thymeleaf.org");
        html.attr("lang", "zh-CN");
        html.appendChild(head);
        html.appendChild(body);
        document.appendChild(html);
        OutputStream stream = new FileOutputStream(htmlFile);
        stream.write("<!DOCTYPE html>".getBytes());
        stream.write(document.toString().getBytes());
    }
}
