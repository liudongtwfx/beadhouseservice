package main.java.com.beadhouse.dynamic.html.areatemplate;

import org.jsoup.nodes.Element;

public class PageTurningStruct extends AbstractAreaStructNode {
    private int currentPage;
    private String pageInfo;
    private String classes;
    private int currPage;
    private int pageSize;

    public PageTurningStruct(int page, int size) {
        super();
        currentPage = page;
        pageSize = size;
    }

    public PageTurningStruct(Element element) {
        super(element);
    }

    @Override
    public void createElementNode() {
        baseStructNode = new Element("div");
        Element pageInfoNode = new Element("div");
        pageInfoNode.append("<div></div");
    }
}
