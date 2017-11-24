package main.java.com.beadhouse.dynamic.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractDomInfo {
    protected String title;
    protected Document document;
    protected Map<Integer, Element> elements;
    protected Map<Integer, NodeInfo> nodeInfoMap;
    protected Map<Integer, List<Integer>> childList;
    protected int id;
    protected int rootid;
    protected String absoluteFilePath;

    public AbstractDomInfo(String absoluteFilePath) {
        id = 0;
        this.absoluteFilePath = absoluteFilePath;
        setDocument();
        elements = new HashMap<>();
        nodeInfoMap = new HashMap<>();
        childList = new HashMap<>();
    }

    private void setDocument() {
        try {
            document = Jsoup.parse(new File(absoluteFilePath), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public Document getDocument() {

        return document;
    }

    public Map<Integer, Element> getElements() {
        return elements;
    }

    public Map<Integer, NodeInfo> getNodeInfoMap() {
        return nodeInfoMap;
    }

    public Map<Integer, List<Integer>> getChildList() {
        return childList;
    }

    public int getRootid() {
        return rootid;
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }
}
