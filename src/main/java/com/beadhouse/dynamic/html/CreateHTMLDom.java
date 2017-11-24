package main.java.com.beadhouse.dynamic.html;

import org.jsoup.nodes.Document;

import java.io.Serializable;

public class CreateHTMLDom extends AbstractDomInfo implements Serializable {
    public CreateHTMLDom(Document document, String absoluteFilePath) {
        super(absoluteFilePath);
    }

}
